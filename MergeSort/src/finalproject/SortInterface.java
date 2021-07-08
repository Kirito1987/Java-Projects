package finalproject;

public interface SortInterface {

    public void recursiveSort(int[] list) throws UnsortedException;

    public void iterativeSort(int[] list) throws UnsortedException;

    public int getCount();

    public long getTime();

}
