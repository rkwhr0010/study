package designpattern.headfirst.chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import designpattern.headfirst.chapter8.TemplateMethodTest3.Coffee;
import designpattern.headfirst.chapter8.TemplateMethodTest3.Tea;

public class TemplateMethodTest4 {
	abstract static class CaffeineBeverageWithHook{
		final void prepareRecipe() {
			 boilWater();
			 brew();
			 purInCup();
			 if(customerWantsCondiments()) {
				 addCondiments();
			 }
		}
		boolean customerWantsCondiments() {
			return true;
		}
		protected abstract void addCondiments();
		protected abstract void brew();
		public void boilWater() {
			System.out.println("물 끊이는 중");
		}
		public void purInCup() {
			System.out.println("컵에 따르는 중");
		}
	}
	
	static class CoffeeWithHook extends CaffeineBeverageWithHook{
		@Override
		public void addCondiments() {
			System.out.println("설탕과 우유를 추가하는 중");
		}
		@Override
		public void brew() {
			System.out.println("필터로 커피를 우려내는 중");
		}
		
		@Override
		public boolean customerWantsCondiments() {
			String answer = getUserInput();
			
			if(answer.equalsIgnoreCase("y")) {
				return true;
			}else {
				return false;
			}
		}
		private String getUserInput() {
			String answer = null;
			System.out.print("커피에 우유와 설탕을 넣을까요?(y/n)");
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				answer = br.readLine();
			}catch(IOException e) {
				System.out.println(e.getCause());
			}
			if(answer == null) {
				return "no";
			}
			return answer;
		}
	}
	static class TeaWithHook extends CaffeineBeverageWithHook{
		@Override
		protected void addCondiments() {
			System.out.println("레몬을 추가하는 중");
		}
		@Override
		protected void brew() {
			System.out.println("찻잎을 우려내는 중");
			
		}
		@Override
		public boolean customerWantsCondiments() {
			String answer = getUserInput();
			if(answer.equalsIgnoreCase("y")) {
				return true;
			}else {
				return false;
			}
		}
		private String getUserInput() {
			String answer = null;
			System.out.println("차에 레몬을 넣을까요? (y/n) ");
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				answer = br.readLine();
			}catch(IOException e) {
				System.out.println(e.getCause());
			}
			if(answer == null) {
				return "no";
			}
			return answer;
		}
	}
	
	public static void main(String[] args) {
		Tea tea = new Tea();
		Coffee coffee = new Coffee();
		tea.prepareRecipe();
		coffee.prepareRecipe();
		
		TeaWithHook teaWithHook = new TeaWithHook();
		CoffeeWithHook coffeeWithHook = new CoffeeWithHook();
		
		System.out.println("\n홍차 준비중...");
		teaWithHook.prepareRecipe();
		System.out.println("\n커피 준비중...");
		coffeeWithHook.prepareRecipe();
	}
}


