package math;

/**
 * Fraction class to avoid IEEE floating arithmetic inaccuracy
 *
 * Each Fraction is represented as nominator/denominator
 *
 * Integers x are represented as x/1
 *
 * Instance methods are implemented with side effects on the object itself. Static methods are their counterpart and are guaranteed to work
 * without side effects
 *
 * Reducing: by default the fractions are NOT reduced. if u with to reduce the fractions after creation/arithmetic u
 * have to do so manually by calling reduce() or its static version
 *
 * @author Philipp von Neffe
 * @since 12/14/2022
 */
public class Fraction implements Comparable<Fraction>{
    private long nominator;
    private long denominator;

    public static final Fraction FRACTION_ZERO = new Fraction(0);
    public static final Fraction FRACTION_ONE = new Fraction(1);


    // all integers x are represented as x/1
    public Fraction(long nominator){
        this.nominator = nominator;
        this.denominator = 1;
    }

    public Fraction(long nominator, long denominator){
        if(denominator == 0)
            throw new FractionDivisionByZero(new Throwable().fillInStackTrace());
        this.nominator = nominator;
        this.denominator = denominator;
        fixSign();
    }

    // copy constructor
    public Fraction(Fraction fraction){
        this.nominator = fraction.nominator;
        this.denominator = fraction.denominator;
        fixSign();
    }

    public long getNominator() {
        return nominator;
    }

    public void setNominator(long nominator) {
        this.nominator = nominator;
        fixSign();
    }

    public long getDenominator() {
        return denominator;
    }

    public void setDenominator(long denominator) {
        if(denominator == 0)
            throw new IllegalArgumentException("Denominator must not be 0!");
        this.denominator = denominator;
        fixSign();
    }

    public Fraction abs(){
        fixSign();
        nominator = isNegative() ? nominator * -1 : nominator;
        return this;
    }

    public static Fraction abs(Fraction f){
        Fraction tmp1 = new Fraction(f);
        tmp1.fixSign();
        tmp1.nominator = tmp1.isNegative() ? tmp1.nominator * -1 : tmp1.nominator;
        return tmp1;
    }

    public Fraction neg(){
        fixSign();
        nominator *= -1;
        return this;
    }

    public static Fraction neg(Fraction f){
        Fraction tmp1 = new Fraction(f);
        tmp1.fixSign();
        tmp1.nominator *= -1;
        return tmp1;
    }

    public Fraction reciprocal(){
        Fraction tmp = new Fraction(this.denominator,this.nominator);
        this.nominator = tmp.nominator;
        this.denominator = tmp.denominator;
        return this;
    }

    public static Fraction reciprocal(Fraction f){
        return new Fraction(f.denominator,f.nominator);
    }

    public boolean isNegative(){
        fixSign();
        return  getSign() < 0;
    }

    public boolean isPositive(){
        fixSign();
        return getSign() > 0;
    }

    public Fraction add(Fraction f){
        Fraction tmp = new Fraction(f);
        long lcm = lcm(tmp);
        Fraction lcmMultiple1 = new Fraction(lcm/this.denominator,lcm/this.denominator);
        Fraction lcmMultiple2 = new Fraction(lcm/tmp.denominator,lcm/tmp.denominator);
        mul(lcmMultiple1);
        tmp.mul(lcmMultiple2);

        this.nominator += tmp.nominator;
        fixSign();
        return this;
    }

    public static Fraction add(Fraction f1, Fraction f2){
        Fraction tmp1 = new Fraction(f1);
        Fraction tmp2 = new Fraction(f2);
        return tmp1.add(tmp2);
    }

    public Fraction sub(Fraction f){
        Fraction tmp = new Fraction(f);
        long lcm = lcm(tmp);
        Fraction lcmMultiple1 = new Fraction(lcm/this.denominator,lcm/this.denominator);
        Fraction lcmMultiple2 = new Fraction(lcm/tmp.denominator,lcm/tmp.denominator);
        mul(lcmMultiple1);
        tmp.mul(lcmMultiple2);

        this.nominator -= tmp.nominator;
        fixSign();
        return this;
    }

    public static Fraction sub(Fraction f1, Fraction f2){
        Fraction tmp1 = new Fraction(f1);
        Fraction tmp2 = new Fraction(f2);
        return tmp1.sub(tmp2);
    }

    public Fraction mul(Fraction f){
        this.nominator *= f.nominator;
        this.denominator *= f.denominator;
        if(denominator == 0)
            throw new FractionDivisionByZero(new Throwable().fillInStackTrace());
        fixSign();
        return this;
    }

    public static Fraction mul(Fraction f1, Fraction f2){
        Fraction tmp1 = new Fraction(f1);
        Fraction tmp2 = new Fraction(f2);
        return tmp1.mul(tmp2);
    }

    public Fraction div(Fraction f){
        return mul(new Fraction(f.denominator,f.nominator)); // a/b / y/z = a/b * z/y
    }

    public static Fraction div(Fraction f1, Fraction f2){
        Fraction tmp1 = new Fraction(f1);
        Fraction tmp2 = new Fraction(f2);
        return tmp1.div(tmp2);
    }

    public Fraction reduce(){
        long gcd = gcd(this);
        this.nominator /= gcd;
        this.denominator /= gcd;
        return this;
    }

    public static Fraction reduce(Fraction f){
        Fraction tmp1 = new Fraction(f);
        long gcd = gcd(tmp1);
        tmp1.nominator /= gcd;
        tmp1.denominator /= gcd;
        return tmp1;
    }

    private void fixSign(){
        if(denominator < 0){
            nominator *= -1;
            denominator *= -1;
        }
    }

    private static Fraction fixSign(Fraction f){
        Fraction tmp1 = new Fraction(f);
        if(tmp1.denominator < 0){
            tmp1.nominator *= -1;
            tmp1.denominator *= -1;
        }
        return tmp1;
    }


    private int getSign(){
        fixSign();
        return  nominator > 0 ? 1 : -1;
    }

    private static int getSign(Fraction f){
        Fraction tmp1 = new Fraction(f);
        tmp1.fixSign();
        return  tmp1.nominator > 0 ? 1 : -1;
    }

    // calculates the gcd of a single fraction
    private static long gcd(Fraction f){
        int sign = f.getSign(); // save sign
        f.abs();  // this version of the euclidean gcd only works with positive values
        long a = f.nominator;
        long b = f.denominator;

        while(a != b){
            if(a > b)
                a -= b;
            else
                b -= a;
        }
        f.nominator *= sign; // restore signess
        return a;
    }

    // calculates the lcm of the denominators of two fractions
    private long lcm(Fraction f){
        return this.denominator * f.denominator / gcd(new Fraction(this.denominator,f.denominator));
    }

    @Override
    public String toString(){
        return nominator + ((denominator != 1) ? "/" + denominator : "");
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        Fraction tmp1 = new Fraction(this).reduce();
        Fraction tmp2 = new Fraction((Fraction) obj).reduce();
        tmp1.fixSign();
        tmp2.fixSign();
        return  (tmp1.getNominator() == tmp2.getNominator()) && (tmp1.getDenominator() == tmp2.getDenominator());
    }

    @Override
    public int compareTo(Fraction fraction) {
        Fraction tmp1 = new Fraction(this).reduce();
        Fraction tmp2 = new Fraction(fraction).reduce();
        if(tmp1.equals(tmp2))
            return 0;

        tmp1.fixSign();
        tmp2.fixSign();
        tmp1.sub(tmp2);

        if(tmp1.isNegative()){
            return -1;
        }else{
            return 1;
        }
    }
}
