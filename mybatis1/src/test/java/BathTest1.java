import com.njupt.mybatis.utils.jdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 传统jdbc批量插入数据
 */
public class BathTest1 {

    @Test
    /**
     * for循环批量插入数据
     * 执行时间  1029 ms
     */
    public void forAdd() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = jdbcUtil.getConnection();
        jdbcUtil.begin(conn);

        String sql = "insert into t_user(username,address) values(?,?)";
        //预编译sql语句
        try {
            Long beginTime = System.currentTimeMillis();
            pstmt = conn.prepareStatement(sql);
            //插入10000条数据，一句一句
            for (int i = 0; i < 10000; i++) {
                pstmt.setString(1, "hello" + (i + 1));
                pstmt.setString(2, "world" + (i + 1));
                pstmt.executeUpdate();
            }
            //提交事务
            jdbcUtil.commit(conn);
            Long endTime = System.currentTimeMillis();
            System.out.println("total Time:" + (endTime - beginTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    /**
     * 批处理方式
     * 执行时间   40ms
     */
    public void batch() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = jdbcUtil.getConnection();
        jdbcUtil.begin(conn);

        String sql = "insert into t_user(username,address) values(?,?)";

        try {
            pstmt = conn.prepareStatement(sql);

            Long beginTime = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                pstmt.setString(1, "hello" + (i + 1));
                pstmt.setString(2, "batch" + (i + 1));

                //每1000条数据进行一次批量操作
                if ((i + 1) % 1000 == 0) {
                    //添加成一批
                    pstmt.addBatch();
                    //执行批处理
                    pstmt.executeBatch();
                    //清空
                    pstmt.clearBatch();
                }
            }
            //提交事务
            jdbcUtil.commit(conn);
            Long endTime = System.currentTimeMillis();

            System.out.println("total Time:" + (endTime - beginTime));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
