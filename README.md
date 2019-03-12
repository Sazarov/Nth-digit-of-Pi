## Pi n-th digit calculator
This app calculates n-th digit of pi (up to 15 significant digits after the decimal). It uses the algorithm described here:

https://www.math.hmc.edu/funfacts/ffiles/20010.5.shtml

### Usage

Install the maven dependencies and run through the PICalculator class' main method. Type a precision between **1 and 15** and it will spit the result out.
Results are not rounded. The tests compare the computed values to `Math.PI` from the java core.

### But why?
I made this to learn more about java and unit testing with junit. 

### TODO
Use `BigDecimal` to calculate with even more precision.