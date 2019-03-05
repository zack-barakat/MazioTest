package com.android.maziotest.rule;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.TimeUnit;

public class TestSchedulersRule implements TestRule {
    private final Scheduler immediate = new Scheduler() {
        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };
    private final TestScheduler testScheduler = new TestScheduler();

    public TestScheduler getTestScheduler() {
        return testScheduler;
    }

    public void advanceTimeBy(long delay, TimeUnit unit) {
        testScheduler.advanceTimeBy(delay, unit);
    }

    @Override
    public Statement apply(final Statement base, Description d) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setIoSchedulerHandler(
                        scheduler -> testScheduler);
                RxJavaPlugins.setComputationSchedulerHandler(
                        scheduler -> testScheduler);
                RxJavaPlugins.setNewThreadSchedulerHandler(
                        scheduler -> testScheduler);
                RxAndroidPlugins.setMainThreadSchedulerHandler(
                        scheduler -> immediate);

                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}