/**  
 * @Title: TestThread.java
 * @Package com.test.thread
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.thread;

/**
 * @ClassName: TestThread
 * @Description: TODO
 */
public class TestThread {

	static class ThreadState implements Runnable {
		public synchronized void waitForAMoment() throws InterruptedException {
			wait(500);
		}

		public synchronized void waitForever() throws InterruptedException {
			wait();
		}

		public synchronized void notifyNow() throws InterruptedException {
			notify();
		}

		@Override
		public void run() {
			try {
				waitForAMoment();

				waitForever();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * @Description: TODO
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		/*Runnable state = new Runnable() {
			@Override
			public void run() {
				try {
					wait(500);
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};*/

		ThreadState state = new ThreadState();
		Thread thread = new Thread(state);
		System.out.println("Create new thread: " + thread.getState());
		thread.start();
		System.out.println("Start the thread: " + thread.getState());
		Thread.sleep(100);
		System.out.println("Waiting for a moment (time): " + thread.getState());
		Thread.sleep(1000);
		System.out.println("Waiting for a moment: " + thread.getState());
		state.notifyNow();
		System.out.println("Wake up the thread: " + thread.getState());
		Thread.sleep(100);
		System.out.println("Terminate the thread: " + thread.getState());

		MyThread mt1 = new MyThread(1);
		MyThread mt2 = new MyThread(2);
		MyThread mt3 = new MyThread(3);
		mt1.start();
		mt2.start();
		mt3.start();

		MyRunnable mt = new MyRunnable();
		new Thread(mt).start();
		new Thread(mt).start();
		new Thread(mt).start();

		System.out.println("TestThread");
	}

	static class MyThread extends Thread {
		private int id = 0;
		private int ticket = 10;

		public MyThread(int id) {
			this.id = id;
		}

		public void run() {
			for (int i = 0; i < 20; i++) {
				if (this.ticket > 0) {
					System.out.println(id + " - 卖票1：ticket" + this.ticket--);
				}
			}
		}
	}

	static class MyRunnable implements Runnable {
		private int ticket = 10;

		public void run() {
			for (int i = 0; i < 20; i++) {
				if (this.ticket > 0) {
					System.out.println("卖票2：ticket" + this.ticket--);
				}
			}
		}
	}
}
