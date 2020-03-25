package pl.srw.countries.util

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxRule(
    val scheduler: Scheduler = Schedulers.trampoline()
) : TestRule {

    override fun apply(base: Statement, description: Description) = object : Statement() {

        private fun before() {
            RxJavaPlugins.setIoSchedulerHandler { scheduler }
            RxJavaPlugins.setComputationSchedulerHandler { scheduler }
            RxJavaPlugins.setNewThreadSchedulerHandler { scheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        }

        override fun evaluate() {
            before()
            try {
                base.evaluate()
            } finally {
                after()
            }
        }

        private fun after() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }
}