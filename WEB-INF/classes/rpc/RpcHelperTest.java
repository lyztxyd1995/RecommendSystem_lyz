����   4 o  rpc/RpcHelperTest  java/lang/Object <init> ()V Code
  	   LineNumberTable LocalVariableTable this Lrpc/RpcHelperTest; testGetJSONArray 
Exceptions  org/json/JSONException RuntimeVisibleAnnotations Lorg/junit/Test;  java/util/HashSet
  	  category one    java/util/Set   add (Ljava/lang/Object;)Z   entity/Item$ItemBuilder
  	 # one
  % & ' 	setItemId -(Ljava/lang/String;)Lentity/Item$ItemBuilder;@@�=p��

  + , - setLatitude (D)Lentity/Item$ItemBuilder;@      
  1 2 - 	setRating
  4 5 6 setCategories *(Ljava/util/Set;)Lentity/Item$ItemBuilder;
  8 9 - setLongitude
  ; < = build ()Lentity/Item; ? two A java/util/ArrayList
 @ 	 D  E java/util/List G org/json/JSONArray
 F 	
 J L K entity/Item M N toJSONObject ()Lorg/json/JSONObject;
 F P Q R put ((Ljava/lang/Object;)Lorg/json/JSONArray;
 T V U rpc/RpcHelper W X getJSONArray &(Ljava/util/List;)Lorg/json/JSONArray;
 Z \ [ %org/skyscreamer/jsonassert/JSONAssert ] ^ assertEquals ,(Lorg/json/JSONArray;Lorg/json/JSONArray;Z)V category Ljava/util/Set; Lentity/Item; listItem Ljava/util/List; 	jsonArray Lorg/json/JSONArray; LocalVariableTypeTable #Ljava/util/Set<Ljava/lang/String;>; Ljava/util/List<Lentity/Item;>; testGetJSONArrayCornerCases empty 
SourceFile RpcHelperTest.java InnerClasses ItemBuilder !               /     *� �    
                                        E     �� Y� L+�  W� Y� !"� $ (� * .� 0+� 3 (� 7� :M� Y� !>� $ (� * .� 0+� 3 (� 7� :N� @Y� B:,� C W-� C W� FY� H:,� I� OW-� I� OW� S� Y�    
   2         7  ]  f  o  x  �  �  � ! � "    >    �      � _ `  7 j # a  ] D ? a  f ; b c  �   d e  f      � _ g  f ; b h   i                   �     ǻ Y� L+�  W� @Y� BM� FY� HN-,� S� Y� Y� !"� $ (� * .� 0+� 3 (� 7� ::� Y� !>� $ (� * .� 0+� 3 (� 7� ::,� C W,� C W-� I� OW-� I� OW-,� S� Y� Y� !� ::-� I� OW-,� S� Y�    
   B    %  &  (  ) ! * * , Q - x . � / � 1 � 2 � 3 � 5 � 7 � 8 � 9    H    �      � _ `   � b c  ! � d e  Q v # a  x O ? a  �  j a  f      � _ g   � b h   k    l m   
   J n 	