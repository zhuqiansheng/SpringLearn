import com.njupt.sc.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class queryTest {
    private JdbcTemplate jdbcTemplate;
    //初始化代码块
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
    }

    @Test
    /**
     * //简单查找,查找学生人数，
     * 多条记录的一个信息，返回一个值
     */
    public void testQuerySimple1() {
        String sql = "select count(*) from student";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("学生总人数=" + count);
    }

    @Test
    /**
     * 简单查找，查找性别为男的所有学生的姓名，返回多个值
     * 一条记录的一个信息
     */
    public void testQuerySimple() {
        String sql = "select name from student where sex=?";
        //返回多个值，用List接收
        List<String> names = jdbcTemplate.queryForList(sql, String.class, "男");   //只要是可以传入Object的都是可以使用？占位符的
        System.out.println(names);
    }

    @Test
    /**
     * 复杂对象，根据id查找其所有信息
     * 一条记录的多个信息
     */
    public void testQueryMap1(){
        String sql = "select * from student where id=?";
        //用Map<String,Object>接收
        Map<String,Object> stu=jdbcTemplate.queryForMap(sql, 3);
        System.out.println(stu);
    }

    @Test
    /**
     * 复杂对象，查找所有学生信息
     * 多条记录的多个信息
     */
    public void testQueryMap2(){
        String sql = "select * from student";
        //用list接收，每个元素是一个Map<String, Object>
        List<Map<String, Object>> stus = jdbcTemplate.queryForList(sql);
        System.out.println(stus);
    }

    @Test
    /**
     * 查找一个学生并封装成对象
     */
    public void testQueryEntity1(){
        String sql = "select * from student where id=?";
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), 4);
        System.out.println(student);

    }

    @Test
    /**
     * 查找多个学生封装成对象放在list中
     */
    public void testQueryEntity2() {
        String sql = "select * from student";
        List<Student> stus = jdbcTemplate.query(sql, new StudentRowMapper());
        System.out.println(stus);
    }


    private class StudentRowMapper implements RowMapper<Student> {
        public Student mapRow(ResultSet rs,int i) throws SQLException {
            Student stu = new Student();
            stu.setId(rs.getInt("id"));
            stu.setName(rs.getString("name"));
            stu.setSex(rs.getString("sex"));
            stu.setBorn(rs.getDate("born"));
            return stu;
        }
    }




}
