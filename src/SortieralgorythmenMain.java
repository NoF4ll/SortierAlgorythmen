import java.util.ArrayList;
import java.util.Random;

public class SortieralgorythmenMain {

	static int a = 100000;

	static int[] unSort = new int[a];
	static int[] sortArray = new int[a];
	static int[] selectSort = new int[a];
	static int[] insertionSort = new int[a];
	static int[] bubbleSort = new int[a];

	static ArrayList<Long> insertionVergleiche = new ArrayList<>();
	static ArrayList<Long> insertionVertausche = new ArrayList<>();
	static ArrayList<Long> insertionZeit = new ArrayList<>();

	static ArrayList<Long> selectionVergleiche = new ArrayList<>();
	static ArrayList<Long> selectionVertausche = new ArrayList<>();
	static ArrayList<Long> selectionZeit = new ArrayList<>();

	static ArrayList<Long> bubbleVergleiche = new ArrayList<>();
	static ArrayList<Long> bubbleVertausche = new ArrayList<>();
	static ArrayList<Long> bubbleZeit = new ArrayList<>();

	static ArrayList<Long> quickVergleiche = new ArrayList<>();
	static ArrayList<Long> quickVertausche = new ArrayList<>();
	static ArrayList<Long> quickZeit = new ArrayList<>();
	// Quicksort Variablen
	
	static long tauschoperationen;
	static long vergleichoperationen;
	static long beginTime;
	static long endTime;

	public static void main(String[] args) {
		
		
			fillArray(sortArray);
			
			for(int i = 0 ; i < 5 ; i++)
			{
				unSort = sortArray.clone();
				insertionSort = insertionSort(unSort);
				unSort = sortArray.clone();
				selectSort = selectionsort(unSort);
				unSort = sortArray.clone();
				bubbleSort = bubblesort(unSort);
				unSort = sortArray.clone();
				beginTime = System.currentTimeMillis();
				quickSort(unSort, 0, unSort.length - 1);
				endTime = System.currentTimeMillis();
				quickZeit.add(endTime-beginTime);
			}
			
		output();
	}

	public static void fillArray(int[] a) {
		Random zufall = new Random();

		for (int i = 0; i < a.length; i++) {
			a[i] = zufall.nextInt(10);
		}
	}

	public static int[] selectionsort(int[] sortieren) {
		long tauschoperationen = 0;
		long vergleichoperation = 0;
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < sortieren.length - 1; i++) {
			for (int j = i + 1; j < sortieren.length; j++) {
				vergleichoperation++;
				if (sortieren[i] > sortieren[j]) {
					int temp = sortieren[i];
					sortieren[i] = sortieren[j];
					sortieren[j] = temp;
					tauschoperationen++;
				}
			}

		}
		selectionVergleiche.add(vergleichoperation);
		selectionVertausche.add(tauschoperationen);
		long endTime = System.currentTimeMillis();
		selectionZeit.add(endTime-beginTime);
		return sortieren;
	}

	public static int[] insertionSort(int[] sortieren) {
		long tauschoperationen = 0;
		long vergleichoperation = 0;
		long beginTime = System.currentTimeMillis();
		int temp;
		for (int i = 1; i < sortieren.length; i++) {
			temp = sortieren[i];
			int j = i;
			vergleichoperation++;
			while (j > 0 && sortieren[j - 1] > temp) {
				tauschoperationen++;
				sortieren[j] = sortieren[j - 1];
				j--;
			}
			sortieren[j] = temp;
		}
		insertionVergleiche.add(vergleichoperation);
		insertionVertausche.add(tauschoperationen);
		long endTime = System.currentTimeMillis();
		insertionZeit.add(endTime-beginTime);
		return sortieren;
	}

	public static int[] bubblesort(int[] zusortieren) {
		long tauschoperationen = 0;
		long vergleichoperation = 0;
		long beginTime = System.currentTimeMillis();
		int temp;
		for (int i = 1; i < zusortieren.length; i++) {
			for (int j = 0; j < zusortieren.length - i; j++) {
				vergleichoperation++;
				if (zusortieren[j] > zusortieren[j + 1]) {
					tauschoperationen++;
					temp = zusortieren[j];
					zusortieren[j] = zusortieren[j + 1];
					zusortieren[j + 1] = temp;
				}
			}
		}
		bubbleVergleiche.add(vergleichoperation);
		bubbleVertausche.add(tauschoperationen);
		long endTime = System.currentTimeMillis();
		bubbleZeit.add(endTime-beginTime);
		return zusortieren;
	}

	static void swap(int[] arr, int i, int j) {

		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		tauschoperationen++;
		quickVertausche.add(tauschoperationen);
	}

	static int partition(int[] arr, int low, int high) {

		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {
			if (arr[j] < pivot) {
				i++;
				vergleichoperationen++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		quickVergleiche.add(vergleichoperationen);
		return (i + 1);
	}

	static void quickSort(int[] arr, int low, int high) {
		 
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
		
	}
	
	static long durchschnittBerechnung(ArrayList<Long> arrayList)
	{
		long a = 0;
		for(int i= 0 ; i < arrayList.size() ; i++)
		{
			a = a + arrayList.get(i);
		}
		return a / arrayList.size();
	}
	static void output()
	{
		System.out.println("Bubblesorttauschoperationen: " + durchschnittBerechnung(bubbleVertausche) + " Vergleichoperationen = "
				+ durchschnittBerechnung(bubbleVergleiche) + " In " + (durchschnittBerechnung(bubbleZeit)) + " MilliSekunden");
		
		System.out.println("Selectiontauschoperationen: " + durchschnittBerechnung(selectionVertausche) + " Vergleichoperationen = "
				+ durchschnittBerechnung(selectionVergleiche) + " In " + (durchschnittBerechnung(selectionZeit)) + " MilliSekunden");
		
		System.out.println("Insertiontauschoperationen: " + durchschnittBerechnung(insertionVertausche) + " Vergleichoperationen = "
				+ durchschnittBerechnung(insertionVergleiche) + " In " + (durchschnittBerechnung(insertionZeit)) + " MilliSekunden");
		System.out.println("Quicktauschoperationen: " + durchschnittBerechnung(quickVertausche) + " Vergleichoperationen = "
				+ durchschnittBerechnung(quickVergleiche) + " In " + (durchschnittBerechnung(quickZeit)) + " MilliSekunden");
	}
}