import java.util.LinkedHashSet;



public class Krzyzowanie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	int zak=20;

	int []points1=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	int []points2=new int[]{1,2,3,4,5,6,20,8,9,10,11,12,13,14,15,16,17,18,19,7};
	int ii=0;
	for(int d=0;d<zak;d++){
			System.out.print(" "+points1[d]);
	}System.out.println(" ");
	for(int d=0;d<zak;d++){
		System.out.print(" "+points2[d]);
	}System.out.println("\n ");
	//LinkedHashSet<MyPoint> r=new LinkedHashSet<>();
	
	int ogr1=(int)(0.7*(double)zak);
	int ogr2=(int)(0.3*(double)zak);
	int ogr3=zak;
	System.out.println("\n "+ogr1+" "+ogr2+" "+ogr3);
	int []temp=new int[ogr1-ogr2];	
	int ij=0;
	
	for(int z=ogr2;z<ogr1;z++){
		temp[ij]=points2[z];ij++;
	}
	
	for(int z=ogr2;z<ogr1;z++){
		boolean bool1=false;
		for(int w =0;w<(ogr1-ogr2);w++){
			
			if(points1[z]==temp[w]){
				bool1=true;
				break;
			}
		}
		//co jesli wartosci z piewszego nie ma w drugim na z miejscu stoi element ktorego nie  ma w b
		if(bool1==false){
			//przeszukamy 2 ciag znajdujac wyraz niebed¹cy w pierwszym jego wartosc zapisaac i potem miejsce z zamienic z miejscem na którym stoi ten element
			int index=0;
			int value=0;
						
			for(int i =0;i<(ogr1-ogr2);i++)	{
				boolean bool2=false;
				for(int j=ogr2;j<ogr1;j++){
					if(points1[j]==temp[i]){
						bool2=true;
					}
				}
				if(bool2==false){
					index=i;
					value=temp[i];
				//	break;
				//}
			//}
			//znalezc w pierwszym zapisan¹ wartoœæ i zamienic z obecnym [z]
			for(int i2 =0;i2<ogr3;i2++){
				if(points1[i2]==value){
					int t=points1[z];
					points1[z]=value;
					points1[i2]=t;
					break;
				}
			}}}
		}
	}
	int ji=0;
	for(int z=ogr2;z<ogr1;z++){
		points1[z]=temp[ji];
		ji++;
	}
	for(int d=0;d<zak;d++){
		System.out.print(" "+points1[d]);
	}System.out.println(" ");
	for(int d=0;d<zak;d++){
		System.out.print(" "+points2[d]);
	}
}}

