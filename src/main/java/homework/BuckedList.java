package homework;

import java.util.*;

public class BuckedList<K> implements Iterable<K>  {

    private int size=1;
    private int iFirst = 0;
    private int iLast = 0;
    private Bucket<K> first;
    private Bucket<K> last;


    public BuckedList() {
        Bucket<K> newList = new Bucket<>();
        first = newList;
        last = newList;
    }

    public int size() {
        if (first == last) {
            return iLast;
        } else {
            return (size-2)*5 + (5-iFirst) + iLast;
        }
    }

    public boolean add(K k) {
        linkLast(k);
        return true;
    }

    public void addLast(K k) {
        linkLast(k);
    }

    public void addFirst(K k) {
        linkFirst(k);
    }

    void linkFirst(K k) {
        if (iFirst == 0) {
            final Bucket<K> b = new Bucket<>();
            first.prev = b;
            b.next = first;
            first = b;

            iFirst=5;
            b.add(--iFirst, k);
            size++;
        } else {
            first.add(--iFirst,k);
        }
    }

    void linkLast(K k) {
        if (iLast > 4) {
            final Bucket<K> b = new Bucket<>();
            last.next = b;
            b.prev = last; // можно через конструктор
            last = b;

            iLast=0;
            b.add(iLast++,k);
            size++;
        } else {
            last.add(iLast++,k);
        }
    }

    public boolean addAll(Collection<? extends K> c) {
        for (K k : c) {
            add(k);
        }
        return true;
    }

    public boolean isEmpty() {
        return (size==1 && iLast==0);
    }

    public boolean contains(Object o) {
        for (K k : this) {
            if (k == (K) o)
                return true;
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    public Iterator<K> iterator() {
        return new BuckedListIterator();
    }

    class BuckedListIterator implements Iterator<K> {

        private Bucket<K> currentBucket = first;
        private int currentIndexIn = iFirst;

        @Override
        public boolean hasNext() {
            if (currentIndexIn < 5)
                return currentBucket.get(currentIndexIn) != null;
            else {        // если индекс крайний, то
                if (currentBucket.next != null) {
                    return (currentBucket.next.get(0) != null);
                } else {
                    return false;
                }
            }
        }

        @Override
        public K next() {
            if (currentIndexIn != 5) {
                int index = currentIndexIn++;
                return currentBucket.get(index++);
            }
            else {
                currentBucket = currentBucket.next;
                currentIndexIn = 0;
                return currentBucket.get(currentIndexIn++);
            }

        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;
        for (K k : this) {
            array[i++] = k;
        }
        return array;
    }

    public void clear() {
        Bucket<K> newBucket = new Bucket<>();
        first = newBucket;
        last = newBucket;
        iFirst = 0;
        iLast = 0;
        size = 1;
    }

    public K get(int index) {
        if (index <= size()) {
            int i = 0;
            for (K k : this) {
                if (i++ == index)
                    return k;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public K remove(int index) {
        Object[] copy = toArray();
        Bucket<K> newBucket = new Bucket<>();
        Bucket<K> newLast = newBucket;
        int newLastInd = 0;
        size=1;
        int j = 0;
        K rem = null;
        for (Object o : copy) {
            if (j!=index) {
                if (newLastInd > 4) {
                    final Bucket<K> b = new Bucket<>();
                    newLast.next = b;
                    b.prev = newLast; // можно через конструктор
                    newLast = b;

                    newLastInd = 0;
                    b.add(newLastInd++, (K) o);
                    size++;
                } else {
                    newLast.add(newLastInd++, (K) o);
                }
            } else {
                rem = (K) o;
            }
            j++;
        }
        iFirst = 0;
        iLast = newLastInd;
        first = newBucket;
        last = newLast;
        return rem;
    }

    public boolean remove(Object o) {
        K rem;
        int index = indexOf(o);
        rem = remove(index);
        return rem != null;
    }

    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return false;
    }

    public int indexOf(Object o) {
        int i = 0;
        for (K k : this) {
            if (k == (K) o)
                return i;
            i++;
        }
        throw new NoSuchElementException();
    }


    private static class Bucket<K> {

        K[] body;
        Bucket<K> next;
        Bucket<K> prev;

        void add(int index, K k) {
            body[index] = k;
        }

        public Bucket() {
            this.body = (K[]) new Object[5];
            this.next = null;
            this.prev = null;
        }

        public Bucket(Bucket<K> next, Bucket<K> prev) {
            this.body = (K[]) new Object[5];
            this.next = next;
            this.prev = prev;
        }

        public K get(int index) {
            return body[index];
        }
    }

    /** //////////////////////////////////////////////////////////////////////////////////////////////*/

    public int lastIndexOf(Object o) {
        return 0;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean addAll(int index, Collection<? extends K> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public K set(int index, K element) {
        return null;
    }

    public void add(int index, K element) {
    }

    public ListIterator<K> listIterator() {
        return null;
    }

    public ListIterator<K> listIterator(int index) {
        return null;
    }

    public List<K> subList(int fromIndex, int toIndex) {
        return null;
    }

}

