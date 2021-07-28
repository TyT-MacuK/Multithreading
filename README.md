Task. Многопоточность

Разработать многопоточное приложение, использующее разделяемые ресурсы. В приложении должна быть реализована функциональность, определенная индивидуальным заданием. 

Требования
1) Любая сущность, желающая получить доступ к разделяемому ресурсу, должна быть потоком. 
2) Программа должна использовать возможности синхронизации, поставляемые библиотеками java.util.concurrent, java.util.concurrent.atomic и java.util.concurrent.locks. 
3) Не использовать synchronized, volatile, а также BlockingQueue и другие ограниченно потокобезопасные коллекции. 
4) Классы и другие сущности приложения должны быть грамотно структурированы по пакетам и иметь отражающую их функциональность название. 
5) Использовать шаблон State для описания состояний объекта, если только этих состояний больше двух. 
6) Для создания потоков разрешается использовать по возможности Callable 
7) Запускать потоки с помощью ExecutorService.
8) Вместо Thread.sleep использовать только возможности перечисления TimeUnit. 
9) Данные инициализации объектов считывать из файла. Данные в файле корректны. 
10) В приложении должен присутствовать потокобезопасный Singleton. При его создании запрещено использовать enum. 
11) Для записи логов использовать Log4J2. 
12) Разрешается для вывода работы потоков использовать метод main.
   
Индивидуальное задание: 

Логистическая база

Автофургоны заезжают на территорию базу для разгрузки или загрузки товаров, которые производятся через терминалы. Если территория заполнена, то фургоны выстраиваются в очередь вне базы Автофургон может только разгружаться или загружаться. Фургоны со скоропортящимся товаром обслуживаются вне очереди. Каждый фургон обязательно должен быть обслужен. 
