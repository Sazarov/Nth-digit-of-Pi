## Pi n-th digit calculator
This app calculates n-th digit of pi. It uses the algorithm described here:

https://www.math.hmc.edu/funfacts/ffiles/20010.5.shtml

The application calculates an **arbitrary number of digits of pi.** You are only limitted by the resources of your machine. Everything is calculated in real time and no caching takes place.

### Usage

Install the maven dependencies and run through the Application class' main method. Type a precision between **1 and `Integer.MAX_VALUE`** and it will spit the result out.
Results are not rounded. The tests compare the computed values to `Math.PI` from the java core.

The application does not test values below the 15th decimal place (precision 15) pending test implementation. There are plenty of 
resources that provide reference values on the internet, feel free to validate your results.

Note that for a large precision calculations will take a while (>10,000).

### But why?
I made this to learn more about java and unit testing with junit.


### TODO
- Add tests to validate large precision calculations
- Optimize