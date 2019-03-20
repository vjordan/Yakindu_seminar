package org.yakindu.scr.seminar;

import org.yakindu.scr.IStatemachine;

public interface ISeminarStatemachine extends IStatemachine {

	public interface SCISeminar {
	
		public void raiseOpen();
		
		public void raiseEnroll();
		
		public void raiseDrop();
		
		public void raiseClose();
		
		public void raiseCancel();
		
		public long getAvailableSeats();
		
		public void setAvailableSeats(long value);
		
		public void setSCISeminarOperationCallback(SCISeminarOperationCallback operationCallback);
	
	}
	
	public interface SCISeminarOperationCallback {
	
		public void addStudent();
		
		public void addToWaitingList();
		
		public void removeStudent();
		
		public void notifyWaitingList();
		
		public void notifyInstructor();
		
	}
	
	public SCISeminar getSCISeminar();
	
}
