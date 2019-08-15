import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class updaeTest {
    private  JdbcTemplate jdbcTemplate;
    //初始化代码块
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
    }

    @Test
    public void testExcute() {

        jdbcTemplate.execute("create table if not exists user1(id int primary key,name varchar(20))");
    }

    @Test
    /**
     * update(sql,new Object[]{})
     */
    public void testUpdate(){
        String sql = "insert into student(name,sex) values(?,?)";
        jdbcTemplate.update(sql, new Object[]{"张飞", "女"});

    }

    @Test
    /**
     * update(sql,Object ... args)
     */
    public void testUpdate2() {
        String sql = "insert into student(name,sex) values(?,?)";
        jdbcTemplate.update(sql, "关羽", "男");
    }

    @Test
    /**
     * 批量更新
     */
    public void testBatchUpdate() {
        String[] sqls={
                "insert into student(name,sex) values('典韦','男')",
                "insert into student(name,sex) values('貂蝉','女')",
                "update student set sex='男' where id=3"
        };
        jdbcTemplate.batchUpdate(sqls);
    }

    @Test
    /**
     *testBatchUpdate(String sql,List<Object[]> args)
     */
    public void testBatchUpdate2() {
        String sql = "insert into selection(student,course) values(?,?)";
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{2, 1001});
        list.add(new Object[]{2, 1002});
        list.add(new Object[]{3, 1003});
        jdbcTemplate.batchUpdate(sql, list);
    }
}
