package ioccontainer;

import annotations.Inject;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SimpleIoCContainer {
    private final Map<Class<?>, Object> registry = new HashMap<>();

    // Метод для регистрации экземпляров
    public void register(Class<?> clazz, Object instance) {
        registry.put(clazz, instance);
    }

    // Метод для разрешения зависимостей
    public <T> T resolve(Class<T> clazz) throws Exception {
        // Проверяем, зарегистрирован ли уже экземпляр для этого класса
        if (registry.containsKey(clazz)) {
            return clazz.cast(registry.get(clazz));
        }

        // Получить все конструкторы
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];
            boolean allInjected = true;

            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].isAnnotationPresent(Inject.class)) {
                    parameters[i] = resolve(parameterTypes[i]);
                } else {
                    allInjected = false;
                }
            }

            if (allInjected) {
                constructor.setAccessible(true);
                T instance = clazz.cast(constructor.newInstance(parameters));
                register(clazz, instance);
                return instance;
            }
        }

        throw new IllegalArgumentException("Не найден подходящий конструктор для " + clazz);
    }
}
