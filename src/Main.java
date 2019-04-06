import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static final int DATA_COUNT = 1000;
    public static void main(String args[]) {
        Main main = new Main();

        int[] dataset = main.initShuffle();
        System.out.println(Arrays.toString(dataset));

        main.selectionSort(dataset.clone());
        main.insertionSort(dataset.clone());
        main.bubbleSort(dataset.clone());
        main.shellSort(dataset.clone(), main);
        int[] quickSortData = dataset.clone();
        main.quickSort(quickSortData, 0, quickSortData.length - 1);
        System.out.println("Quick Sort : " + Arrays.toString(quickSortData));

        main.radixSort(dataset.clone());

        int[] mergeSortData = dataset.clone();
        main.mergeSort(mergeSortData, 0, mergeSortData.length - 1);
        System.out.println("Merge Sort : " + Arrays.toString(mergeSortData));


    }

    private void heapSort(int[] dataset) {

    }

    private void mergeSort(int[] dataset, int left, int right) {
        int mid;

        if (left < right) {   // 분할이 다 되지 않았을 경우 if 문 실행
            mid = (left + right) / 2;

            mergeSort(dataset, left, mid);      //왼쪽 블록 분할
            mergeSort(dataset, mid + 1, right);  //오른쪽 블록 분할
            merge(dataset, left, mid, right);   //분할된 블록 병합
        }
    }

    private void merge(int[] dataset, int left, int mid, int right) {
        int i, j, k, m;

        i = left;
        j = mid + 1;
        k = left;    //결과 배열의 인덱스

        int[] tempArray = new int[dataset.length];

        //left부터 mid 까지의 블록과 mid+1부터 right까지의 블록을 서로 비교하는 부분
        while (i <= mid && j <= right) {
            if (dataset[i] < dataset[j]){   //left index 값이 right index 값보다 작으면 left index 값을 결과 result에 복사
                tempArray[k] = dataset[i];
                i++;
            }else{        //아니라면 right index 값을 결과 result에 복사
                tempArray[k] = dataset[j];
                j++;
            }
            k++;
        }

        // left 블록의 값은 다 처리되었는데 right 블록의 index가 아직 남아있을 경우
        // right index를 순차적으로 결과 result에 복사
        if (i > mid){
            for (m = j; m <= right; m++){
                tempArray[k] = dataset[m];
                k++;
            }
        } else {                    // left 블록의 index가 아직 남아있을 경우 left index를 순차적으로 결과 result에 복사
            for (m = i; m <= mid; m++){
                tempArray[k] = dataset[m];
                k++;
            }
        }

        for(m = left; m <= right; m++){
            dataset[m] = tempArray[m];
        }
    }

    private void radixSort(int[] dataset) {
        ArrayDeque[] deque = new ArrayDeque[10];
        for(int i=0; i<10; i++) {
            deque[i] = new ArrayDeque();
        }

        int max = Arrays.stream(dataset).max().getAsInt();

        for(int i=0; i<Math.log10(max) + 1; i++) {
            for(int data : dataset) {
                int index = data % (int)Math.pow(10, i + 1) / (int)Math.pow(10, i);
                deque[index].push(data);
            }
            int cnt = 0;
            for(ArrayDeque ad : deque) {
                while (!ad.isEmpty()) {
                    dataset[cnt] = (int)ad.pollLast();
                    cnt++;
                }
            }
        }

        System.out.println("Radix Sort : " + Arrays.toString(dataset));
    }

    private void quickSort(int[] dataset, int left, int right) {
        if(left >= right) {
            return;
        }

        int pivot = left;
        int i = pivot + 1, j = right;
        int tmp;

        while(i <= j) {  // 포인터가 엇갈릴때까지 반복
            while(i <= right && dataset[i] <= dataset[pivot]){
                i++;
            }
            while(j > left && dataset[j] >= dataset[pivot]){
                j--;
            }

            if(i > j){  // left와 right 만남
                tmp = dataset[j];
                dataset[j] = dataset[pivot];
                dataset[pivot] = tmp;
            } else {    // data swap
                tmp = dataset[i];
                dataset[i] = dataset[j];
                dataset[j] = tmp;
            }
        }

        quickSort(dataset, left, j - 1);
        quickSort(dataset, j + 1, right);
    }

    // gap 값으로 간격을 주어 삽입 정렬
    private void shellSort(int[] dataset, Main main) {
        int[] tmpList;
        int div = 2;
        for(int gap=dataset.length / div; gap >= 1; div *= 2, gap = dataset.length / div) {
            for(int i=0; i<gap; i++) {

                int cnt = dataset.length / gap;
                tmpList = new int[cnt];
                for(int j=0; j<cnt; j++) {
                    tmpList[j] = dataset[gap * j + i];
                }
                main.insertionSort(tmpList);

                for(int j=0; j<tmpList.length; j++) {
                    dataset[gap * j + i] = tmpList[j];
                }
            }
        }

        System.out.println("Shell Sort : " + Arrays.toString(dataset));
    }

    private void bubbleSort(int[] dataset) {
        for(int i=0; i<dataset.length; i++) {
            for(int j=0; j<dataset.length - i - 1; j++) {
                if(dataset[j] > dataset[j+1]) {
                    int tmp = dataset[j+1];
                    dataset[j+1] = dataset[j];
                    dataset[j] = tmp;
                }
            }
        }

        System.out.println("Bubble Sort : " + Arrays.toString(dataset));
    }

    private void insertionSort(int[] dataset) {
        for(int i=1; i<dataset.length; i++) {
            for(int j=i; j>=0; j--) {
                if(j == 0 || dataset[j - 1] < dataset[j]) {
                    break;
                } else {
                    int tmp = dataset[j];
                    dataset[j] = dataset[j-1];
                    dataset[j-1] = tmp;
                }
            }
        }

        System.out.println("Insertion Sort : " + Arrays.toString(dataset));
    }

    private void selectionSort(int[] dataset) {
        for(int i=0; i<dataset.length; i++) {
            int min = dataset[i], index = i;
            for(int j=i+1; j<dataset.length; j++) {
                if(min > dataset[j]) {
                    min = dataset[j];
                    index = j;
                }
            }

            if(i != index) {
                int tmp = dataset[i];
                dataset[i] = min;
                dataset[index] = tmp;
            }
        }

        System.out.println("Selection Sort : " + Arrays.toString(dataset));
    }

    private int[] initShuffle() {
        int[] dataset = new int[DATA_COUNT];
        for(int i=0; i<DATA_COUNT; i++) {
            dataset[i] = i;
        }

        // Shuffle
        Random rnd = ThreadLocalRandom.current();
        for(int i=dataset.length - 1; i>0; i--) {
            int index = rnd.nextInt(i + 1);

            int tmp = dataset[index];
            dataset[index] = dataset[i];
            dataset[i] = tmp;
        }

        return dataset;
    }


    //binarySearch. 없을 시, 작은값 마지막 인덱스 반환
    private static int binarySearch(int[] arr, int search) {
        int size = arr.length, start = 0, end = size - 1;

        while (true) {
            int mid = (start + end) / 2;
            if (arr[mid] == search) {
                return mid;
            } else if(start == end) {
                return start;   // 작은값 마지막 인덱스
            } else if (arr[mid] > search) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
}
