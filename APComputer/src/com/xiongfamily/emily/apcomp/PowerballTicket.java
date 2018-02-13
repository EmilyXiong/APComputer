package com.xiongfamily.emily.apcomp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PowerballTicket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TreeSet<Integer> numbers = new TreeSet<Integer>();
		TreeMap<Integer, Integer>[] all = new TreeMap[6];
		for (int i =0; i< 6; i++){
			all[i] = new TreeMap<Integer, Integer>();
		}
		for (int k = 0; k < 68; k++){
			int i = 0;
			while(i < 5){
				int num = (int)(Math.random()*69 + 1);
				if(!numbers.contains(num)){
					numbers.add(num);
					Integer count = all[1].get(num);
					if (count != null){
						all[i].put(num, count+1);
					}
					else{
						all[i].put(num, 1);
					}
					i++;
				}
			}
			for(Integer in : numbers){
				System.out.printf("%4s", in);
			}
			numbers.clear();
			int pball= (int)(Math.random()*26 + 1);
			int count = all[5].get(pball) == null ? 1 : all[5].get(pball)+1;
			all[5].put(pball, count);
			System.out.printf("%8d\n\n",  pball);
		}
		
		System.out.print("\n\n");
		
		for (int j=0; j<6; j++){
			
			int number=0, count=20000;
			for (int key :all[j].keySet() ){
				if(all[j].get(key) < count){
					number = key;
					count = all[j].get(key);
				}
			}
			System.out.printf("%5d",  number);
	
		}
	}

}
