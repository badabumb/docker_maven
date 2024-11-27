package com.example;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
public class App {
    public static void main(String[] args) throws IOException {
        // Создание HTTP-сервера, который слушает на порту 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHandler()); // Установка обработчика для корневого контекста
        server.setExecutor(null); // Создает стандартный исполнитель
        server.start(); // Запуск сервера
        System.out.println("Server is listening on port 8080...");
    }
    // Обработчик запросов
    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Docker with Maven!";
            exchange.sendResponseHeaders(200, response.getBytes().length); // Отправка заголовков ответа
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes()); // Отправка тела ответа
            os.close(); // Закрытие потока
        }
    }
}