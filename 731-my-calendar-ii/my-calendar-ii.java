class MyCalendarTwo {
    private List<int[]> bookings = new ArrayList<>();
    private List<int[]> overlaps = new ArrayList<>();

    public MyCalendarTwo() {}

    public boolean book(int start, int end) {
        for (int[] o : overlaps)
            if (start < o[1] && end > o[0]) return false; // triple booking

        for (int[] b : bookings)
            if (start < b[1] && end > b[0]) 
                overlaps.add(new int[]{Math.max(start, b[0]), Math.min(end, b[1])});

        bookings.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(startTime,endTime);
 */