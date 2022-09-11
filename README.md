# WorkManagerStatesSampleApp
# This app is basic intro of work manager to see how it works and gives resultes.
# What is work manager?
  WorkManager is latest technology in Android to handle background tasks instead of using Service or Intent service in most of the cases. It's best preferred in Jetpack components because it provides constraints to be set for its process. It is very efficient so if app is closed or open it will continue to work with provided constraint.
  
# Types of WorkManager?
  1) OneTimeWorkManager -> It is best to use it for background related tasks need to be executed for once only. Live data provided by work manager can be used to observe  background task results.
  
  2) PeriodicWorkMananger -> It is best to be used for background tasks need repitition in intervals. Intervals can be defined in                         Microseconds,Minutes,Days,Hours,Milliseconds,Nanoseconds and seconds. After each interval defined the last enqueued work request will be executed.
  
# Work Manager States?
  1) Succeeded
  2) Blocked
  3) Cancelled
  4) Enqueued
  5) Running
  6) Failed
  
# Work Manager Constraints?
  1) requiresStorageNotLow
  2) requiresBatteryNotLow
  3) requiresCharging
  4) requiresDeviceIdle
  5) getRequiredNetworkType
