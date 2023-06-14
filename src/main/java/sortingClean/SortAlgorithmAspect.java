package sortingClean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class SortAlgorithmAspect {
    private Map<String, Long> timingMap = new HashMap<>();
    private Map<String, Integer> countMap = new HashMap<>();
    private long totalTiming = 0;

    @Pointcut("execution(void sortingClean.SortingAlgorithm+.sort(..))")
    public void sortingAlgorithmExecution() {}

    @Around("sortingAlgorithmExecution()")
    public void aroundSortingAlgorithmExecution(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        pjp.proceed();
        long executionTime = System.currentTimeMillis() - start;

        String className = pjp.getTarget().getClass().getSimpleName();

        timingMap.put(className, timingMap.getOrDefault(className, 0L) + executionTime);
        countMap.put(className, countMap.getOrDefault(className, 0) + 1);
        totalTiming += executionTime;
    }

    public void printReport() {
        System.out.println("The total time of running all sort functions was " + totalTiming + " ms");
        System.out.println("In detail:");

        for (String key : timingMap.keySet()) {
            System.out.println("Function sort in " + key + " ran " + countMap.get(key) + " times and took in total " + timingMap.get(key) + " ms");
        }
    }
}
