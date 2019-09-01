import com.njupt.mybatis.bean.Person;
import com.njupt.mybatis.dao.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class parameterTest {

    public static SqlSessionFactory sqlSessionFactory = null;

    public  SqlSessionFactory getSqlSessionFactory(){
        if (sqlSessionFactory == null) {
            String source = "mybatis-config.xml";
            try {
                Reader reader = Resources.getResourceAsReader(source);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    @Test
    /**
     * 根据ID删除对应Person 数据
     */
    public void deletePersonTest(){
        //获得SQLSession
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();
        //通过反射获取PersonMapper对象
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        //删除id=2的person
        personMapper.deletePerson(2);
        //提交事务
        sqlSession.commit();
    }


    @Test
    /**
     * 根据姓名和性别查找person
     */
    public void testPersonByNameAndGender(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

//        JavaBean方式
//        System.out.println("JavaBean方式");
//        Person person = personMapper.getPersonByNameAndGender1("zhangsan", "F");
//        System.out.println(person);


//        POJO方式
        System.out.println("POJO方式");
        Person person2 = personMapper.getPersonByNameAndGender2(new Person("zhangsan", "F"));
        System.out.println(person2);

//        map方式
        System.out.println("map方式");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "zhangsan");
        param.put("gender", "F");
        Person person3 = personMapper.getPersonByNameAndGender3(param);
        System.out.println(person3);

//        注解方式
        System.out.println("注解方式");
        Person person4 = personMapper.getPersonByNameAndGender4("zhangsan", "F");
        System.out.println(person4);

    }

    @Test
    /**
     *  集合类型参数
     */
    public void testCollection(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

//        Person person = personMapper.getPersonByCollection1(Arrays.asList(1, 2));
        Person person = personMapper.getPersonByCollection2(new int[]{1,2});
        System.out.println(person);
    }

    @Test
    /**
     * foreach
     */
    public void testForeach() {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        List<Person> personLIst = personMapper.getPersonsByIds(new int[]{1, 2, 3, 4, 5});
        System.out.println(personLIst);
    }

    @Test
    /**
     * mybatis批量添加
     */
    public void ProcessMybatisBath(){
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        List<Person> personList = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person("tom" + i, "123456", "F");
            personList.add(person);
        }
        personMapper.addPersons(personList);

        //提交事务
        sqlSession.commit();
    };

    @Test
    public void testBatchForExecutor() {
        //使sqlSession获得批处理的能力
        SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH);
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        for (int i = 0; i < 10000; i++) {
            personMapper.addPerson(new Person("tom" + i, "tom@qq.com", "F"));
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
