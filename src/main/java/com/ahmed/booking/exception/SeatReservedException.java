package com.ahmed.booking.exception;

public class SeatReservedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 357170685480210855L;

	public SeatReservedException() {
        super();
    }

    public SeatReservedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatReservedException(String message) {
        super(message);
    }

    public SeatReservedException(Throwable cause) {
        super(cause);
    }
}
