# viewFlipper
ViewFlipper

(1). slide animation effect 
    Android:fromXDelta (starting X offset, float or percentage) 
    Android:toXDelta (Ending X offset, float or percentage) 
    slide_left_in：android:fromXDelta="-100%" android:toXDelta="0%"  (from left in)
    slide_left_out：android:fromXDelta="0% android:toXDelta="-100%" (from left out)
    slide_right_in：android:fromXdelta="100%" to android:toXDelta="0%" (from right in)
    slide_right_out:android:fromXDelta="0%" android:toXDelta="100%" (from right out) 

    
(2). animation function:
    initial animation object：AnimationUtils.loadAnimation(this,animation xml files)
    setInAnimation
    setOutAnimation
    
(3). viewFlipper.showPrevious() ：show previous page
   viewFlipper.showNext() ：show next page
    
(4). Activity.onTouchEventListener 
   triger MotionEvent.Action_UP, MotionEvent.Action_DOWN
