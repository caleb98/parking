

public interface Hardware {

    public Card scanCard();
    public void PrintTicket(Transaction transaction);
    public void PrintReceipt(Transcation transaction);
    public void setGateOpen();
    public void setGateClosed();
    public int scanTicket();

}