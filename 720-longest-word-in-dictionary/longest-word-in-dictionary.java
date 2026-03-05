class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words, (a, b) -> 
            a.length() == b.length() ? a.compareTo(b) : a.length() - b.length());
        Set<String> built = new HashSet<>();
        String ans = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                built.add(w);
                if (w.length() > ans.length() || 
                   (w.length() == ans.length() && w.compareTo(ans) < 0)) ans = w;
            }
        }
        return ans;
    }
}