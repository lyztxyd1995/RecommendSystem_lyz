����   4 g  external/GeoHash  java/lang/Object BASE_32 Ljava/lang/String; ConstantValue 	  0123456789bcdefghjkmnpqrstuvwxyz <init> ()V Code
   
  LineNumberTable LocalVariableTable this Lexternal/GeoHash; divideRangeByValue (D[D)I
     middle ([D)D value D range [D mid StackMapTable@        encodeGeohash (DDI)Ljava/lang/String;�V�     @V�     �f�     @f�      , java/lang/StringBuilder
 + 
  /  
 1 3 2 java/lang/String 4 5 charAt (I)C
 + 7 8 9 append (C)Ljava/lang/StringBuilder;
 + ; < = length ()I
 + ? @ A toString ()Ljava/lang/String; latitude 	longitude 	precision I latRange lonRange isEven Z bit base32CharIndex geohash Ljava/lang/StringBuilder;  main ([Ljava/lang/String;)V	 R T S java/lang/System U V out Ljava/io/PrintStream;@L�	V��@$Л��*
  \ ! "
 ^ ` _ java/io/PrintStream a b println (Ljava/lang/String;)V args [Ljava/lang/String; 
SourceFile GeoHash.java !                 
      /     *� �                        
       u     ,� J&)�� 	,)R�,)R�           	  
                                     �  
       6     *1*1c o�                        	 ! "         ��Y #RY %R:�Y 'RY )R:666	� +Y� -:
� P� 	x(� .�6	� 	x&� .�6	� � 6� 	�� 
	� 0� 6W66	
� :���
� >�       R       "  %  (  +  4  7   < ! I " L # Y & e ( k ) n * q + ~ , � - �  � 1    \ 	   � B      � C     � D E   � F   " r G   % o H I  ( l J E  + i K E 	 4 ` L M 
     � 7 	 N N +  @ 	 O P     @     � Q W Y� [� ]�       
    6  7         c d    e    f