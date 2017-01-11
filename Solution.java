
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samson
 */
public class Solution {
    
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = "input.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int[] res;
        
        String[] _followGraph_nodesm = in.nextLine().split(" ");
        int _followGraph_nodes = Integer.parseInt(_followGraph_nodesm[0]);
        int _followGraph_edges = Integer.parseInt(_followGraph_nodesm[1]);
        
        int[] _followGraph_from = new int[_followGraph_edges];
        int[] _followGraph_to = new int[_followGraph_edges];
        
        for (int _followGraph_i = 0; _followGraph_i < _followGraph_edges; _followGraph_i++) {
        	String[] _followGraph_fromv = in.nextLine().split(" ");
            _followGraph_from[_followGraph_i] = Integer.parseInt(_followGraph_fromv[0]);
            _followGraph_to[_followGraph_i] = Integer.parseInt(_followGraph_fromv[1]);
        }
        
        
        String[] _likeGraph_nodesm = in.nextLine().split(" ");
        int _likeGraph_nodes = Integer.parseInt(_likeGraph_nodesm[0]);
        int _likeGraph_edges = Integer.parseInt(_likeGraph_nodesm[1]);
        
        int[] _likeGraph_from = new int[_likeGraph_edges];
        int[] _likeGraph_to = new int[_likeGraph_edges];
        
        for (int _likeGraph_i = 0; _likeGraph_i < _likeGraph_edges; _likeGraph_i++) {
        	String[] _likeGraph_fromv = in.nextLine().split(" ");
            _likeGraph_from[_likeGraph_i] = Integer.parseInt(_likeGraph_fromv[0]);
            _likeGraph_to[_likeGraph_i] = Integer.parseInt(_likeGraph_fromv[1]);
        }
        
        // generate follow Graph
        int[][] followGraph = new int[_followGraph_edges][2];
        for (int i = 0; i < _followGraph_edges; i++) {
            followGraph[i][0] = _followGraph_from[i];
            followGraph[i][1] = _followGraph_to[i];
        }
        // generate like Graph
        int[][] likeGraph = new int[_likeGraph_edges][2];
        for (int i = 0; i < _likeGraph_edges; i++) {
            likeGraph[i][0] = _likeGraph_from[i];
            likeGraph[i][1] = _likeGraph_to[i];
        }
        int _targetUser;
        _targetUser = Integer.parseInt(in.nextLine().trim());
        
        int _minLikeThreshold;
        _minLikeThreshold = Integer.parseInt(in.nextLine().trim());
        
        res = getRecommendedTweets(followGraph, likeGraph, _targetUser, _minLikeThreshold);
        if (res == null) {
            bw.write("Null Result");
            bw.newLine();
            bw.close();
            return;
        }
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
            System.out.println(res[res_i]);
        }
        bw.close();
    }
    
    
    static int[] getRecommendedTweets(int[][] follow, int[][] likes, int tU, int threshold){
        ArrayList<Integer> tF = new ArrayList<>();
        HashMap <Integer, Integer> pRel = new HashMap<>();
        ArrayList<Integer> rel = new ArrayList<>();
        int no;
        int twID;
        for(int i = 0; i < follow.length; i++){
            if(follow[i][0] == tU){
                tF.add(follow[i][1]);
            }
        }//end for follow
        
        for(int i = 0; i < likes.length; i++){
            if(tF.contains(likes[i][0])){
                twID = likes[i][1];
                //no = ;
                if(pRel.get(twID) != null){
                    no = pRel.get(twID);
                    pRel.put(twID, no+1);
                }
                else{
                    pRel.put(twID, 1);
                }
            }
        }//end for likes
        for(Integer k : pRel.keySet()){
            if(pRel.get(k) >= threshold)
                rel.add(k);
        }
        int c = 0;
        int [] retV = new int [rel.size()];
        for (int i : rel)
            retV[c++] = i;
        return retV;
    }//end method getRecommendedTweets
}
    

