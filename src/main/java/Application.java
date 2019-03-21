import ui.UserInterface;

public class Application {
	
	public static void main(String[] args) {
		new UserInterface().run();
//		runConcurrent();
//		runRegular();
	}
	
//	public static void runConcurrent() {
//		int precision = 200_00;
//		int numberOfThreads = 4;
//		ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);
//		Future<BigDecimal>[] results = new Future[numberOfThreads];
//
//		int[] ranges = new int[numberOfThreads+1];
//
//		for (int i = 0; i < ranges.length; i++) {
//			ranges[i] = i*(precision/numberOfThreads);
//		}
//		System.out.println("Start: " + LocalTime.now());
//		System.out.println("Ranges: " + Arrays.toString(ranges));
//
//		List<ConcurrentRunner> calcList = new ArrayList<>();
//		for (int i = 0; i < numberOfThreads; i++) {
//			calcList.add(i, new ConcurrentRunner(ranges[i], ranges[i + 1], precision));
//			results[i] = es.submit(calcList.get(i));
//		}
//
//		Timer t = new Timer();
//		int loopTime = 3000;
//		t.scheduleAtFixedRate(
//				new TimerTask() {
//					private double oldProgress;
//					private int secondsSoFar;
//					public void run() {
//
//						double avgProgress = 0;
//						secondsSoFar += (loopTime/1000);
//
//						for (ConcurrentRunner calc :
//								calcList) {
////							long secondsLeft = (long)(1/((calc.getProgress() - progress)/loopTime));
//
////							Duration timeLeft = Duration.ofSeconds(secondsLeft);
////							System.out.println("old progress: " + progress);
////							System.out.println("new progress: " + calc.getProgress());
////							System.out.println("Time left: " + secondsLeft/60 + " minutes left");
//
//							System.out.println("Worker " + calc.getWorkerNumber() + " is at " + Math.round(calc.getProgress()*100*100.0)/100.0 + " %");
//
//							avgProgress += calc.getProgress();
////							oldProgress = calc.getProgress();
//						}
//
//						avgProgress = avgProgress/calcList.size();
//
//						double progressDelta = avgProgress - oldProgress;
//						oldProgress = avgProgress;
//
//						int loopTimeInSeconds = loopTime/1000;
//						double progressPerSecond = progressDelta/loopTimeInSeconds;
//						double secondsLeft = 1/progressPerSecond;
//
////						System.out.println("Ran for: " + (int)(secondsSoFar/60) +"m " + 60%secondsSoFar + "s");
//						System.out.println("Ran for: " + secondsSoFar + "s");
//						System.out.println("Minutes left: " + (int)(secondsLeft/60));
//
//					}
//				},
//				0,      // run first occurrence immediately
//				loopTime);  // run every three seconds
//
//
//		BigDecimal pi = new BigDecimal(0);
//
//		for (Future<BigDecimal> result :
//				results) {
//			try {
//				pi = pi.add(result.get());
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
//		es.shutdown();
//		t.cancel();
//
//		System.out.println("Pi: " + pi);
//		System.out.println("End: " + LocalTime.now());
//	}
//
//	public static void runRegular() {
//		PiCalculator calc = new PiCalculator(20000);
//		System.out.println("Start: " + LocalTime.now());
//		BigDecimal pi = calc.calculatePi();
//
//		System.out.println(pi);
//
//		System.out.println("End: " + LocalTime.now());
//	}
}
