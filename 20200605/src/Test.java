/*
 * @program: 20200605
 * @description
 * @author: mrs.yang
 * @create: 2020 -06 -05 21 :46
 */

import java.util.Scanner;
import java.util.Stack;

public class Test {

        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if(stack1.empty()&&stack2.empty()){
                return -1;
            }
            if(!stack2.empty()){
                return stack2.pop();
            }
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
  public static int func(int[] arr,int s,int k){
          if(s==0){
              return 1;
          }
          if(s<0||s>0&&k<0){
              return 0;
          }
          return func(arr,s-arr[k],k-1)+func(arr,s,k-1);
  }
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        while(scan.hasNext()){
            int n=scan.nextInt();
            int[] arr=new int[n+1];
            for (int i = 1; i <=n; i++) {
                arr[i]=scan.nextInt();
            }
            func(arr,40,n);
        }
    }
    public static void main2(String[] args) {
        int x,y;
        x=5>>2;
        y=x>>>2;
        System.out.println(y);
    }
    public static void main1(String[] args) {
        StringBuffer a=new StringBuffer("A");
        StringBuffer b=new StringBuffer("B");
        operation(a,b);
        System.out.println(a+" "+b);
    }
    static void operation(StringBuffer x,StringBuffer y){
        x.append(y);
        y=x;
    }
}
