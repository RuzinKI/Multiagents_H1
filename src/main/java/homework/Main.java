package homework;

public class Main {
    public static void main(String[] args) {
        BuckedList<Integer> list2 = new BuckedList<>();

        list2.add(1);
        System.out.println(list2.isEmpty());
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list2.addFirst(0);
        list2.addFirst(-1);
        list2.addFirst(-2);
        list2.addFirst(-3);
        list2.addFirst(-4);
        list2.addFirst(-5);
        list2.addFirst(-6);
        list2.addFirst(-7);
        list2.addFirst(-8);
        list2.addFirst(-9);

//        for (Integer integer : list2) {
//            System.out.println(integer);
//        }

//        List<Integer> integers = List.of(1,2,3,4,5,6);
//        list2.addAll(integers);

        System.out.println("test");

        for (Integer integer : list2) {
            System.out.println(integer);
        }

        boolean gf = list2.remove((Object) 3);
        System.out.println(gf);

        System.out.println("after remove");

        for (Integer integer : list2) {
            System.out.println(integer);
        }
    }
}
