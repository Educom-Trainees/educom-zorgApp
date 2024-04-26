export default function isSubsequence(s, t) {
    if (typeof (s) != 'string' || typeof (t) != 'string') return false;
    if (s.length > t.length) return false;
    if (s.length == 0) return true;

    var i = 0, j = 0;

    while (i < s.length && j < t.length) {
        if (s[i] == t[j]) {
            i++;
            j++;
        } else {
            j++;
        }
    }

    return i == s.length;
}