public enum CurrentState {
    START, BUY, FILL, TAKE, REMAINING, EXIT;

    enum BUYING {
        START, COMMAND
    }

    enum FILLING {
        START, WATER, MILK, BEANS, CUPS
    }

    public static CurrentState getState(String state) {
        for (CurrentState value : CurrentState.values()) {
            if (value.toString().equals(state.toUpperCase())) return value;
        }
        return START;
    }
}