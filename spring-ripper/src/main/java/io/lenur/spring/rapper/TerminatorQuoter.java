package io.lenur.spring.rapper;

@Profiling
public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    public void initBean() {
        System.out.println("Phase 2");
        System.out.println(repeat);
    }

    public void destroyBean() {
        System.out.println("The bean TerminatorQuoter was destroyed!");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("message " + message);
        }
        System.out.println("Phase 3");
    }
}
