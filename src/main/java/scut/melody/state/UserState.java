package scut.melody.state;

public enum  UserState {

    lock(1,"锁定"),unlock(2,"活动");
    private String value;
    private int index;
   private UserState(int index,String value){
    this.index = index;
    this.value = value;
   }
}
