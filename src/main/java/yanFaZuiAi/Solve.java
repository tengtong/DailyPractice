package yanFaZuiAi;

import java.math.BigInteger;

public class Solve {
    public String solve (String s, String t) {
        BigInteger integerS = new BigInteger(s);
        BigInteger integerT = new BigInteger(t);
        BigInteger integerRet = integerS.add(integerT);
        return integerRet.toString();
    }
}
