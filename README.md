
# Паттерны (шаблоны) проектирования

### ✨Немного теории

🛠️ **Паттерны** - это общие решения для типичных проблем, возникающих при разработке программного обеспечения. Они предоставляют архитектурные концепции, которые можно использовать в проектах для улучшения модульности, расширяемости и повторного использования кода.

Описание ряда шаблонов проектирования:
1. **Шаблон Одиночка (Singleton)**. Гарантирует, что класс имеет только один экземпляр и предоставляет глобальную точку доступа к этому экземпляру.

2. **Шаблон Абстрактная Фабрика (Abstract Factory)**. Предоставляет интерфейс для создания семейств взаимосвязанных или зависимых объектов без указания их конкретных классов.

3. **Шаблон Фабричный метод (Factory Method)**. Определяет интерфейс для создания объектов в суперклассе, но оставляет подклассам решение о том, какой класс создавать, делегируя ответственность за создание объектов подклассам.

4. **Шаблон Строитель (Builder)**. Позволяет создавать объекты с различными параметрами конфигурации, облегчая процесс создания сложных объектов.

5. **Шаблон Прототип (Prototype)**. Позволяет создавать новые объекты на основе копии существующего объекта, избегая сложных операций инициализации.

6. **Шаблон Адаптер (Adapter)**. Позволяет объектам с несовместимыми интерфейсами работать вместе путем обертывания одного объекта вокруг другого.

7. **Шаблон Декоратор (Decorator)**. Позволяет добавлять новую функциональность объектам динамически, без изменения их интерфейса.

8. **Шаблон Фасад (Facade)**. Предоставляет унифицированный интерфейс для набора интерфейсов в подсистеме, облегчая использование этой подсистемы.

9. **Шаблон Компоновщик (Composite)**. Позволяет сгруппировать объекты в древовидные структуры для представления их в виде единого объекта.

10. **Шаблон Наблюдатель (Observer)**. Позволяет объектам подписываться и отписываться от изменений в других объектах.

### 🚀Практика

В данной работе представлена реализация задания, связанного с вышеописанной темой:
1. Рефакторинг ранее реализованного задания с применением паттерна.

## Задание

Найти в своём коде нереализованный паттерн (код написан «в лоб», без применения паттерна), реализовать его. Прислать две ссылки: на старый код (без паттерна) и новый код (с реализованным паттерном), с указанием какой паттерн был применён.

## Описание результатов

🤔 В качестве задания для реализации рефакторинга было выбрано [**задание**](https://github.com/MironovNikita/sber-homework11) по созданию пулов потоков [**`FixedThreadPool`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/threadPool/FixedThreadPool.java) и [**`ScalableThreadPool`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/threadPool/ScalableThreadPool.java). Подробно об этих пулах потоков можно прочитать в [**описании**](https://github.com/MironovNikita/sber-homework11/blob/main/README.md) задания.

В качестве паттерна проектирования для реализации был выбран "_Параметризованный фабричный метод_". **Фабричный метод** — это _порождающий_ шаблон проектирования, который используется для создания объектов без необходимости знания конкретного класса объекта. Параметризованный фабричный метод является вариантом этого шаблона, который позволяет создавать объекты различных типов, в зависимости от переданных параметров.

Реализация данного паттерна расположена в пакете [**poolFactory**](https://github.com/MironovNikita/sber-homework12/tree/main/src/main/java/org/example/poolFactory).

Данная директория содержит 2 файла:
- [**`ThreadPoolType`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/poolFactory/ThreadPoolType.java) - перечисление типов пулов потоков;
- [**`ThreadPoolFactory`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/poolFactory/ThreadPoolFactory.java) - непосредственно сама фабрика пулов потоков.

**`ThreadPoolType`** содержит два параметра перечисления:
- __FIXED__ - памаметр создания пула потоков фиксированного размера;
- __SCALABLE__ - параметр создания пула потоков с минимальным и максимальным количеством потоков.

**`ThreadPoolFactory`** в свою очередь содержит всего 1 метод: 
```java
public ThreadPool createThreadPool(ThreadPoolType poolType, int... sizes),
```
где:
1. _ThreadPoolType poolType_ - тип пула потоков, который мы хотим создать;
2. _int... sizes_ - переменное число аргументов типа `int`. 

####

__int... sizes__ позволяет нам менять количество аргументов в зависимости от реализации. В случае [**`FixedThreadPool`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/threadPool/FixedThreadPool.java) нам нужен всего 1 параметр: неизменное количество потоков. В случае с [**`ScalableThreadPool`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/threadPool/ScalableThreadPool.java) нам нужно уже два параметра: минимальное и максимальное количество потоков. Такое представление аргументов называется **"varargs"**(variable arguments). Мы можем передавать любое количество целочисленных значений, которые будут представлять размеры пула (например, минимальный размер, максимальный размер и т.д.), и все они будут доступны внутри метода как массив `sizes`. Это позволит нам гибко добавлять различные реализации пулов потоков, если это будет необходимо.

Непосредственно реализация классов пулов потоков осталась неизменной. Изменениям подвергся класс, отвечающий за тестовый запуск пулов потоков в классе [**`Main`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/Main.java). Это класс [**`TaskRunner`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/task/TaskRunner.java).

В данном классе осуществлялось создание пулов потоков и дальнейшая работа с ними. Так, если раньше пулы потоков создавались достаточно просто:
```java
//Метод task1()
ThreadPool fixedThreadPool = new FixedThreadPool(3);

//Метод task2()
ThreadPool scalableThreadPool = new ScalableThreadPool(2, 5);
```

То теперь создание пулов потоков осуществляется с помощью фабричного метода:
```java
//Метод task1()
ThreadPoolFactory poolFactory = new ThreadPoolFactory();
poolTest(poolFactory.createThreadPool(ThreadPoolType.FIXED, 3));

//Метод task2()
ThreadPoolFactory poolFactory = new ThreadPoolFactory();
poolTest(poolFactory.createThreadPool(ThreadPoolType.SCALABLE, 2, 5));
```

Стоит указать, что для "чистоты кода" также одинаковый код двух методов task1() и task2() был вынесен в отдельный метод: `private static void poolTest(ThreadPool threadPool)`. 

Таким образом, можем сравнить, как выглядел код [**до**](https://github.com/MironovNikita/sber-homework11/blob/main/src/main/java/org/example/task/TaskRunner.java) рефакторинга:

```java
public class TaskRunner {
    public static void task1() {
        System.out.println("Выбрано: Проверка работы FixedThreadPool");

        Counter counter = new Counter();
        ThreadPool fixedThreadPool = new FixedThreadPool(3);
        fixedThreadPool.start();

        for (int i = 1; i <= 5; i++) {
            int threadNumber = i;

            fixedThreadPool.execute(() -> {
                System.out.println("\nЗадача " + threadNumber + " выполняется потоком " +
                        Thread.currentThread().getName());
                double answer = 0;
                answer += counter.count(threadNumber);
                System.out.println("\nОтвет потока " + Thread.currentThread().getName()
                        + " для значения " + threadNumber + " равен " + answer);
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fixedThreadPool.interrupt();
    }

    public static void task2() {
        System.out.println("Выбрано: Проверка работы ScalableThreadPool");

        Counter counter = new Counter();
        ThreadPool scalableThreadPool = new ScalableThreadPool(2, 5);
        scalableThreadPool.start();

        for (int i = 1; i <= 10; i++) {
            int threadNumber = i;

            scalableThreadPool.execute(() -> {
                System.out.println("\nЗадача " + threadNumber + " выполняется потоком " +
                        Thread.currentThread().getName());
                double answer = 0;
                answer += counter.count(threadNumber);
                System.out.println("\nОтвет потока " + Thread.currentThread().getName() + " равен " + answer);
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scalableThreadPool.interrupt();
    }
}
```

И как он выглядит [**после**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/task/TaskRunner.java):

```java
public class TaskRunner {
    public static void task1() {
        System.out.println("Выбрано: Проверка работы FixedThreadPool");

        ThreadPoolFactory poolFactory = new ThreadPoolFactory();
        poolTest(poolFactory.createThreadPool(ThreadPoolType.FIXED, 3));
    }

    public static void task2() {
        System.out.println("Выбрано: Проверка работы ScalableThreadPool");

        ThreadPoolFactory poolFactory = new ThreadPoolFactory();
        poolTest(poolFactory.createThreadPool(ThreadPoolType.SCALABLE, 2, 5));
    }

    private static void poolTest(ThreadPool threadPool) {
        Counter counter = new Counter();
        threadPool.start();

        for (int i = 1; i <= 10; i++) {
            int threadNumber = i;

            threadPool.execute(() -> {
                System.out.println("\nЗадача " + threadNumber + " выполняется потоком " +
                        Thread.currentThread().getName());
                double answer = 0;
                answer += counter.count(threadNumber);
                System.out.println("\nОтвет потока " + Thread.currentThread().getName()
                        + " для значения " + threadNumber + " равен " + answer);
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.interrupt();
    }
}
```

Код стал более приятным, лаконичным и читаемым. Также теперь можно быстро как добавить новую реализацию настраиваемого пула потоков, так и быстро реализовать его проверку.

### 🗳️ Немного о структуре 📇

При запуске приложения в классе [**`Main`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/Main.java) мы увидим следующее меню:

![mainMenu](https://github.com/MironovNikita/sber-homework12/blob/main/res/mainMenu.png)

В классе **`Main`** осуществляется работа с [**`TaskHandler`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/task/TaskHandler.java). Данный класс отвечает за работу с пользователем и за вызов соответствующих методов [**`TaskRunner`**](https://github.com/MironovNikita/sber-homework12/blob/main/src/main/java/org/example/task/TaskRunner.java). Подробно с запуском и тестированием пулов потоков можно ознакомиться [**здесь**](https://github.com/MironovNikita/sber-homework11/).

### 💡 Примечание

Тесты написаны с помощью библиотеки JUnit (*junit-jupiter*). Соответствующая зависимость добавлена в [**`pom.xml`**](https://github.com/MironovNikita/sber-homework11/blob/main/pom.xml) задания, в котором был реализован паттерн "_Фабричный метод_".

Версия зависимости прописана в блоке *properties /properties*:

```java
<junit.version>5.10.1</junit.version>
```

Результат сборки текущего проекта:

```java
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.680 s
[INFO] Finished at: 2024-02-09T21:22:53+03:00
[INFO] ------------------------------------------------------------------------
```