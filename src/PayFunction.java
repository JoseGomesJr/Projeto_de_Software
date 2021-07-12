import java.util.*;
public class PayFunction {
    private PaymentMethod pMethod;
    public PaymentMethod AddMethod(){
        Scanner input= new Scanner(System.in);
        System.out.printf("Select a payment method:\n1-Check by post\n2-Check in hand\n3-Bank Account\n");
        int nSelect= input.nextInt();
        switch (nSelect) {
            case 1:
                this.pMethod= new PaymentMethod("Check by post");
                break;
            case 2:
                this.pMethod= new PaymentMethod("Check in hand");
                break;
            case 3:
                PayBanck();
                break;    
            default:
                break;
        }
       return this.pMethod;
    }
    private void PayBanck(){
        Scanner input= new Scanner(System.in);
        System.out.println("Inform the bank account where the amount will be deposited:");
        System.out.println("Bank name:");
        String nameBank= input.nextLine();
        System.out.println("Type Account:\nEnter the type of account, example: checking account");
        String typeAccount = input.nextLine();
        System.out.println("Number Account:\nAccount number must be a number");
        int numbeAccount= input.nextInt();
        this.pMethod= new PaymentMethod("Bank Account", numbeAccount, nameBank, typeAccount);
    }
    

}
