import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

//Запуск сервера
public class Launcher {
    public static void main(String[] args) throws Exception {
        int port = 8080; //Номер порта

        //Создание сервлета пользовательского класса
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        //Контейнер сервлета
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //Добавление сервлета в контейнер, сервлет слушает все запросы к серверу
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        //Создание сервера Jetty на порту 8080
        Server server = new Server(port);
        //Привязка обработчика (сервлета) к серверу
        server.setHandler(context);

        //Запуск сервера
        try {
            server.start();
            System.out.println("Started on port: " + port);
            System.out.println("http://localhost:" + port);
            server.join();
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
}
