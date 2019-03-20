package org.yakindu.scr.seminar;

public class SeminarStatemachine implements ISeminarStatemachine {

	protected class SCISeminarImpl implements SCISeminar {
	
		private SCISeminarOperationCallback operationCallback;
		
		public void setSCISeminarOperationCallback(
				SCISeminarOperationCallback operationCallback) {
			this.operationCallback = operationCallback;
		}
		private boolean open;
		
		public void raiseOpen() {
			open = true;
		}
		
		private boolean enroll;
		
		public void raiseEnroll() {
			enroll = true;
		}
		
		private boolean drop;
		
		public void raiseDrop() {
			drop = true;
		}
		
		private boolean close;
		
		public void raiseClose() {
			close = true;
		}
		
		private boolean cancel;
		
		public void raiseCancel() {
			cancel = true;
		}
		
		private long availableSeats;
		
		public long getAvailableSeats() {
			return availableSeats;
		}
		
		public void setAvailableSeats(long value) {
			this.availableSeats = value;
		}
		
		protected void clearEvents() {
			open = false;
			enroll = false;
			drop = false;
			close = false;
			cancel = false;
		}
	}
	
	protected SCISeminarImpl sCISeminar;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_Scheduled,
		main_region_Open,
		main_region_Full,
		main_region_Closed,
		main_region__final_,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	public SeminarStatemachine() {
		sCISeminar = new SCISeminarImpl();
	}
	
	public void init() {
		this.initialized = true;
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCISeminar.setAvailableSeats(0);
	}
	
	public void enter() {
		if (!initialized) {
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		}
		enterSequence_main_region_default();
	}
	
	public void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public boolean isActive() {
		return stateVector[0] != State.$NullState$;
	}
	
	/** 
	* @see IStatemachine#isFinal()
	*/
	public boolean isFinal() {
		return (stateVector[0] == State.main_region__final_);
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCISeminar.clearEvents();
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_Scheduled:
			return stateVector[0] == State.main_region_Scheduled;
		case main_region_Open:
			return stateVector[0] == State.main_region_Open;
		case main_region_Full:
			return stateVector[0] == State.main_region_Full;
		case main_region_Closed:
			return stateVector[0] == State.main_region_Closed;
		case main_region__final_:
			return stateVector[0] == State.main_region__final_;
		default:
			return false;
		}
	}
	
	public SCISeminar getSCISeminar() {
		return sCISeminar;
	}
	
	private boolean check_main_region_Scheduled_tr0_tr0() {
		return (sCISeminar.open) && (sCISeminar.getAvailableSeats()>0);
	}
	
	private boolean check_main_region_Scheduled_tr1_tr1() {
		return sCISeminar.cancel;
	}
	
	private boolean check_main_region_Open_tr0_tr0() {
		return (sCISeminar.enroll) && (sCISeminar.getAvailableSeats()==0);
	}
	
	private boolean check_main_region_Open_tr1_tr1() {
		return sCISeminar.cancel;
	}
	
	private boolean check_main_region_Open_tr2_tr2() {
		return (sCISeminar.enroll) && (sCISeminar.getAvailableSeats()>0);
	}
	
	private boolean check_main_region_Open_tr3_tr3() {
		return sCISeminar.close;
	}
	
	private boolean check_main_region_Full_tr0_tr0() {
		return sCISeminar.close;
	}
	
	private boolean check_main_region_Full_tr1_tr1() {
		return sCISeminar.cancel;
	}
	
	private boolean check_main_region_Full_tr2_tr2() {
		return sCISeminar.drop;
	}
	
	private boolean check_main_region_Closed_tr0_tr0() {
		return sCISeminar.cancel;
	}
	
	private void effect_main_region_Scheduled_tr0() {
		exitSequence_main_region_Scheduled();
		enterSequence_main_region_Open_default();
	}
	
	private void effect_main_region_Scheduled_tr1() {
		exitSequence_main_region_Scheduled();
		enterSequence_main_region__final__default();
	}
	
	private void effect_main_region_Open_tr0() {
		exitSequence_main_region_Open();
		sCISeminar.operationCallback.addToWaitingList();
		
		enterSequence_main_region_Full_default();
	}
	
	private void effect_main_region_Open_tr1() {
		exitSequence_main_region_Open();
		enterSequence_main_region__final__default();
	}
	
	private void effect_main_region_Open_tr2() {
		exitSequence_main_region_Open();
		sCISeminar.operationCallback.addStudent();
		
		enterSequence_main_region_Open_default();
	}
	
	private void effect_main_region_Open_tr3() {
		exitSequence_main_region_Open();
		enterSequence_main_region_Closed_default();
	}
	
	private void effect_main_region_Full_tr0() {
		exitSequence_main_region_Full();
		enterSequence_main_region_Closed_default();
	}
	
	private void effect_main_region_Full_tr1() {
		exitSequence_main_region_Full();
		enterSequence_main_region__final__default();
	}
	
	private void effect_main_region_Full_tr2() {
		exitSequence_main_region_Full();
		sCISeminar.operationCallback.notifyWaitingList();
		
		enterSequence_main_region_Full_default();
	}
	
	private void effect_main_region_Closed_tr0() {
		exitSequence_main_region_Closed();
		enterSequence_main_region__final__default();
	}
	
	/* Entry action for state 'Closed'. */
	private void entryAction_main_region_Closed() {
		sCISeminar.operationCallback.notifyInstructor();
	}
	
	/* 'default' enter sequence for state Scheduled */
	private void enterSequence_main_region_Scheduled_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Scheduled;
	}
	
	/* 'default' enter sequence for state Open */
	private void enterSequence_main_region_Open_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Open;
	}
	
	/* 'default' enter sequence for state Full */
	private void enterSequence_main_region_Full_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Full;
	}
	
	/* 'default' enter sequence for state Closed */
	private void enterSequence_main_region_Closed_default() {
		entryAction_main_region_Closed();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Closed;
	}
	
	/* Default enter sequence for state null */
	private void enterSequence_main_region__final__default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region__final_;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state Scheduled */
	private void exitSequence_main_region_Scheduled() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Open */
	private void exitSequence_main_region_Open() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Full */
	private void exitSequence_main_region_Full() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Closed */
	private void exitSequence_main_region_Closed() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for final state. */
	private void exitSequence_main_region__final_() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_Scheduled:
			exitSequence_main_region_Scheduled();
			break;
		case main_region_Open:
			exitSequence_main_region_Open();
			break;
		case main_region_Full:
			exitSequence_main_region_Full();
			break;
		case main_region_Closed:
			exitSequence_main_region_Closed();
			break;
		case main_region__final_:
			exitSequence_main_region__final_();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state Scheduled. */
	private void react_main_region_Scheduled() {
		if (check_main_region_Scheduled_tr0_tr0()) {
			effect_main_region_Scheduled_tr0();
		} else {
			if (check_main_region_Scheduled_tr1_tr1()) {
				effect_main_region_Scheduled_tr1();
			}
		}
	}
	
	/* The reactions of state Open. */
	private void react_main_region_Open() {
		if (check_main_region_Open_tr0_tr0()) {
			effect_main_region_Open_tr0();
		} else {
			if (check_main_region_Open_tr1_tr1()) {
				effect_main_region_Open_tr1();
			} else {
				if (check_main_region_Open_tr2_tr2()) {
					effect_main_region_Open_tr2();
				} else {
					if (check_main_region_Open_tr3_tr3()) {
						effect_main_region_Open_tr3();
					}
				}
			}
		}
	}
	
	/* The reactions of state Full. */
	private void react_main_region_Full() {
		if (check_main_region_Full_tr0_tr0()) {
			effect_main_region_Full_tr0();
		} else {
			if (check_main_region_Full_tr1_tr1()) {
				effect_main_region_Full_tr1();
			} else {
				if (check_main_region_Full_tr2_tr2()) {
					effect_main_region_Full_tr2();
				}
			}
		}
	}
	
	/* The reactions of state Closed. */
	private void react_main_region_Closed() {
		if (check_main_region_Closed_tr0_tr0()) {
			effect_main_region_Closed_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__final_() {
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Scheduled_default();
	}
	
	public void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		clearOutEvents();
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_Scheduled:
				react_main_region_Scheduled();
				break;
			case main_region_Open:
				react_main_region_Open();
				break;
			case main_region_Full:
				react_main_region_Full();
				break;
			case main_region_Closed:
				react_main_region_Closed();
				break;
			case main_region__final_:
				react_main_region__final_();
				break;
			default:
				// $NullState$
			}
		}
		clearEvents();
	}
}
