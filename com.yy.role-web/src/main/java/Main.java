import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.flywaydb.core.Flyway;

import java.io.File;

/**
 * Created by heren on 2014/10/13.
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        String webappDirLocation = "com.yy.role-web/src/main/webapp/";
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8083";
        }

        File file = new File(webappDirLocation);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
        } else {
            System.out.println("路径有问题");
        }
        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext root = new WebAppContext();
        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        root.setParentLoaderPriority(true);
        server.setHandler(root);
        try {
            migrationDb();
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 数据库版本控制
     */
    public static void migrationDb() {

        Flyway flyway = new Flyway();
        //设置数据库链接配置
        //flyway.setDataSource("jdbc:mysql://127.0.0.1:3306/jims-his","root","123456");
        flyway.setDataSource("jdbc:mysql://127.0.0.1:3306/yy_sys","root","123456");
        //设置schema用户
       // flyway.setSchemas("his");
        //flyway.setTable("SCHEMA_VERSION");
        flyway.setEncoding("UTF-8");
//        flyway.setValidateOn.Migrate(true);
        //清空所有表结构
        flyway.clean();
//        flyway.setTargetAsString("1");
        //初始化flyWAy
        //执行版本控制
        flyway.migrate();
        //我的测试提交
    }
}
