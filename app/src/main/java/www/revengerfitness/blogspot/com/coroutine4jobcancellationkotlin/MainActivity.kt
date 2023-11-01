package www.revengerfitness.blogspot.com.coroutine4jobcancellationkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.Exception


/**
 * Job hierarcy : job1 is parent and job2,job3 is child
 * job1 {
 *        job2{}
 *        job3{}
 *
 *        }*/
class MainActivity : AppCompatActivity() {
    private val TAG = "prashant chauhan"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.Main).launch {
            execute()
        }
    }

    /**  private suspend fun execute() {
    val parentJob = GlobalScope.launch(Dispatchers.Main) {
    Log.d(TAG,"parent - $coroutineContext")
    val childJob=launch {// u can use Dispatchers.IO with launch.
    Log.d(TAG,"child - $coroutineContext") // child can overwrite context but by default it uses parent context.
    }
    }
    }*/

    private suspend fun execute() {
        /**
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
        Log.d(TAG,"parent started")
        val childJob=launch {// u can use Dispatchers.IO with launch.
        try {
        Log.d(TAG,"child job started") // child can overwrite context but by default it uses parent context.
        delay(5000)
        Log.d(TAG,"child job ended")
        }catch (e:CancellationException) {
        Log.d(TAG,"child job cancellation")
        }

        }
        delay(3000)
        //Log.d(TAG,"child job cancelled")
        childJob.cancel()
        Log.d(TAG,"parent ended")
        }

        // delay(1000)
        //parentJob.cancel() // force cancellation of parent job without completing child jobs
        parentJob.join()// waiting until parent completed
        Log.d(TAG,"parent completed")
         */

        val paretJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000) {
                if (isActive) { // check whether coroutine is in active state or not
                    executeLongRunningTask()
                    Log.d(TAG, i.toString())
                }
            }
        }
        delay(100)
        Log.d(TAG, "cancelling job")
        paretJob.cancel()
        paretJob.join()
        Log.d(TAG, "parent completed")
    }


    private fun executeLongRunningTask() {
        for (i in 1..10000000) {

        }

    }
}