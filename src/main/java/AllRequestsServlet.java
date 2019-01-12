import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//Сервлет
public class AllRequestsServlet extends HttpServlet {
    //Обработка GET запросов
    //Загрузка веб-страницы
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String page = null; //Строка с веб-страницей
        String filePath = "./src/main/resources/page.html"; //Путь к файлу

        //Чтение файла со страницей
        try {
            page = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        //Отправка страницы в качестве ответа
        response.getWriter().print(page);
        response.setContentType("text/html;charset=utf-8"); //Установка кодировки
        response.setStatus(HttpServletResponse.SC_OK); //Установка статуса ответа (200 - ок)
    }

    //Обработка POST запросов
    //Получение и обработка данных из формы
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        //Получение данных из формы
        String name = request.getParameter("firstname");
        if(name == null || name.isEmpty()){
            //Если сообщение отсутствует, устанавливаем статус 403
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/html;charset=utf-8"); //Установка кодировки
            response.getWriter().print("No data!"); //Вывод сообщения
        } else{
            //Возврат страницы с  полученными данными в виде ответа сервера
            response.setStatus(HttpServletResponse.SC_OK); //Установка статуса ответа (200 - ок)
            response.setContentType("text/html;charset=utf-8"); //Установка кодировки
            response.getWriter().print("Your data: " + request.getParameterMap().toString()); //Вывод сообщения
        }
    }
}
