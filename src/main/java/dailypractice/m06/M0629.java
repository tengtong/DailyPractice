package dailypractice.m06;

/**
 * 168. Excel表列名称
 * @author tengtong
 */
public class M0629 {
    /**
     * 从 1 开始的 26 进制转换题
     * @param cn
     * @return
     */
    public String convertToTitle(int cn) {
        StringBuilder sb = new StringBuilder();
        while (cn > 0) {
            cn--;
            sb.append((char)(cn % 26 + 'A'));
            cn /= 26;
        }
        sb.reverse();
        return sb.toString();
    }
}
