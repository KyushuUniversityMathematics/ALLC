/* LPGeneratorTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. LPGeneratorTokenManager.java */
import java.util.*;
import java.io.*;

/** Token Manager. */
@SuppressWarnings("unused")public class LPGeneratorTokenManager implements LPGeneratorConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0){
   switch (pos)
   {
      case 0:
         if ((active0 & 0xbb9b8L) != 0L)
         {
            jjmatchedKind = 22;
            return 25;
         }
         if ((active0 & 0x640L) != 0L)
         {
            jjmatchedKind = 22;
            return 15;
         }
         if ((active0 & 0x40000L) != 0L)
         {
            jjmatchedKind = 22;
            return 12;
         }
         return -1;
      case 1:
         if ((active0 & 0x40000L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 1;
            return 11;
         }
         if ((active0 & 0xbbef0L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 1;
            return 25;
         }
         if ((active0 & 0x108L) != 0L)
            return 25;
         return -1;
      case 2:
         if ((active0 & 0x40000L) != 0L)
            return 10;
         if ((active0 & 0x10640L) != 0L)
            return 25;
         if ((active0 & 0xab8b0L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 22;
               jjmatchedPos = 2;
            }
            return 25;
         }
         return -1;
      case 3:
         if ((active0 & 0xab8e0L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 3;
            return 25;
         }
         if ((active0 & 0x10L) != 0L)
            return 25;
         return -1;
      case 4:
         if ((active0 & 0xab8c0L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 4;
            return 25;
         }
         if ((active0 & 0x20L) != 0L)
            return 25;
         return -1;
      case 5:
         if ((active0 & 0x880c0L) != 0L)
            return 25;
         if ((active0 & 0x23800L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 5;
            return 25;
         }
         return -1;
      case 6:
         if ((active0 & 0x1800L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 6;
            return 25;
         }
         if ((active0 & 0x22000L) != 0L)
            return 25;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0){
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 51;
         return jjMoveStringLiteralDfa1_0(0x100000000000L);
      case 37:
         return jjStopAtPos(0, 50);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x4000000000L);
      case 40:
         return jjStopAtPos(0, 24);
      case 41:
         return jjStopAtPos(0, 25);
      case 42:
         jjmatchedKind = 47;
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 43:
         jjmatchedKind = 45;
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 44:
         return jjStopAtPos(0, 29);
      case 45:
         jjmatchedKind = 46;
         return jjMoveStringLiteralDfa1_0(0x1100000000L);
      case 47:
         jjmatchedKind = 49;
         return jjMoveStringLiteralDfa1_0(0x1000400000000L);
      case 58:
         return jjStopAtPos(0, 26);
      case 59:
         return jjStopAtPos(0, 23);
      case 60:
         jjmatchedKind = 42;
         return jjMoveStringLiteralDfa1_0(0x10000000000L);
      case 61:
         jjmatchedKind = 30;
         return jjMoveStringLiteralDfa1_0(0x80000000000L);
      case 62:
         jjmatchedKind = 41;
         return jjMoveStringLiteralDfa1_0(0x8000000000L);
      case 63:
         return jjStopAtPos(0, 35);
      case 91:
         return jjStopAtPos(0, 52);
      case 93:
         return jjStopAtPos(0, 53);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x90L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x640L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x80108L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x1800L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x20L);
      case 123:
         return jjStopAtPos(0, 27);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x2000000000L);
      case 125:
         return jjStopAtPos(0, 28);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(1, 38);
         break;
      case 47:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 48);
         break;
      case 61:
         if ((active0 & 0x80000000L) != 0L)
            return jjStopAtPos(1, 31);
         else if ((active0 & 0x100000000L) != 0L)
            return jjStopAtPos(1, 32);
         else if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 33);
         else if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(1, 34);
         else if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(1, 39);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(1, 40);
         else if ((active0 & 0x80000000000L) != 0L)
            return jjStopAtPos(1, 43);
         else if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(1, 44);
         break;
      case 62:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(1, 36);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x41000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L);
      case 102:
         if ((active0 & 0x8L) != 0L)
            return jjStartNfaWithStates_0(1, 3, 25);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x2800L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x10L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000L);
      case 110:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(1, 8, 25);
         break;
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x240L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x28400L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 124:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStopAtPos(1, 37);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x22000L);
      case 102:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(2, 16, 25);
         break;
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0xa0L);
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 110:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(2, 10, 25);
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 114:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 2;
         }
         else if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(2, 18, 10);
         return jjMoveStringLiteralDfa3_0(active0, 0x40L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x10L);
      case 120:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 101:
         if ((active0 & 0x10L) != 0L)
            return jjStartNfaWithStates_0(3, 4, 25);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x1800L);
      case 106:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x20L);
      case 111:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 101:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(4, 5, 25);
         return jjMoveStringLiteralDfa5_0(active0, 0x28000L);
      case 108:
         return jjMoveStringLiteralDfa5_0(active0, 0x40L);
      case 109:
         return jjMoveStringLiteralDfa5_0(active0, 0x1800L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000L);
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa6_0(active0, 0x20000L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x1800L);
      case 108:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(5, 6, 25);
         break;
      case 114:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(5, 15, 25);
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 115:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(5, 7, 25);
         break;
      case 116:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(5, 19, 25);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 116:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(6, 17, 25);
         break;
      case 121:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(6, 13, 25);
         break;
      case 122:
         return jjMoveStringLiteralDfa7_0(active0, 0x1800L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 101:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(7, 11, 25);
         else if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(7, 12, 25);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 47;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 12:
               case 25:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  { jjCheckNAdd(25); }
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  { jjCheckNAdd(25); }
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  { jjCheckNAdd(25); }
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 20)
                        kind = 20;
                     { jjCheckNAddStates(0, 2); }
                  }
                  else if ((0x100002600L & l) != 0L)
                  {
                     if (kind > 1)
                        kind = 1;
                  }
                  else if (curChar == 35)
                  {
                     if (kind > 2)
                        kind = 2;
                     { jjCheckNAddStates(3, 5); }
                  }
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  { jjCheckNAdd(25); }
                  break;
               case 1:
                  if (curChar != 35)
                     break;
                  if (kind > 2)
                     kind = 2;
                  { jjCheckNAddStates(3, 5); }
                  break;
               case 2:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  { jjCheckNAddStates(3, 5); }
                  break;
               case 3:
                  if ((0x2400L & l) != 0L && kind > 2)
                     kind = 2;
                  break;
               case 4:
                  if (curChar == 10 && kind > 2)
                     kind = 2;
                  break;
               case 5:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 42:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  { jjCheckNAddStates(0, 2); }
                  break;
               case 43:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  { jjCheckNAdd(43); }
                  break;
               case 44:
                  if ((0x3ff000000000000L & l) != 0L)
                     { jjCheckNAddTwoStates(44, 45); }
                  break;
               case 45:
                  if (curChar == 46)
                     { jjCheckNAdd(46); }
                  break;
               case 46:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  { jjCheckNAdd(46); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 12:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     { jjCheckNAdd(25); }
                  }
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 11:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     { jjCheckNAdd(25); }
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               case 10:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     { jjCheckNAdd(25); }
                  }
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     { jjCheckNAdd(25); }
                  }
                  else if (curChar == 64)
                     jjstateSet[jjnewStateCnt++] = 24;
                  if (curChar == 103)
                     { jjAddStates(6, 7); }
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 21;
                  else if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 15;
                  else if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 15:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 22)
                        kind = 22;
                     { jjCheckNAdd(25); }
                  }
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 2:
                  if (kind > 2)
                     kind = 2;
                  { jjAddStates(3, 5); }
                  break;
               case 6:
                  if (curChar == 101 && kind > 14)
                     kind = 14;
                  break;
               case 7:
                  if (curChar == 108)
                     { jjCheckNAdd(6); }
                  break;
               case 8:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 13:
                  if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
               case 27:
                  if (curChar == 101)
                     { jjCheckNAdd(6); }
                  break;
               case 16:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 121 && kind > 14)
                     kind = 14;
                  break;
               case 18:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 19:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 23:
                  if (curChar == 64)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 24:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  { jjCheckNAdd(25); }
                  break;
               case 25:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  { jjCheckNAdd(25); }
                  break;
               case 26:
                  if (curChar == 103)
                     { jjAddStates(6, 7); }
                  break;
               case 28:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 30:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 32:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 33:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 32;
                  break;
               case 34:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 33;
                  break;
               case 35:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 34;
                  break;
               case 36:
                  if (curChar == 108 && kind > 14)
                     kind = 14;
                  break;
               case 37:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 38;
                  break;
               case 40:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 39;
                  break;
               case 41:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 40;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  { jjAddStates(3, 5); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 47 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   43, 44, 45, 2, 3, 5, 35, 41, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, "\151\146", "\145\154\163\145", "\167\150\151\154\145", 
"\146\157\162\141\154\154", "\145\170\151\163\164\163", "\151\156", "\146\157\162", "\146\165\156", 
"\155\151\156\151\155\151\172\145", "\155\141\170\151\155\151\172\145", "\154\151\142\162\141\162\171", null, 
"\156\165\155\142\145\162", "\144\145\146", "\163\165\142\152\145\143\164", "\166\141\162", 
"\151\155\160\157\162\164", null, null, null, "\73", "\50", "\51", "\72", "\173", "\175", "\54", "\75", 
"\53\75", "\55\75", "\52\75", "\57\75", "\77", "\55\76", "\174\174", "\46\46", "\76\75", 
"\74\75", "\76", "\74", "\75\75", "\41\75", "\53", "\55", "\52", "\57\57", "\57", "\45", 
"\41", "\133", "\135", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public LPGeneratorTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public LPGeneratorTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream)
  {
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 47; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x3ffffffffffff9L, 
};
static final long[] jjtoSkip = {
   0x6L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[47];
    private final int[] jjstateSet = new int[2 * 47];

    
    protected char curChar;
}
