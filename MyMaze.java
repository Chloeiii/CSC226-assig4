//CSC226 assignment4
//Yaxi Yu    
//V00828218
/*============================================================*/
//      8
//    1 * 4    <-- encodings of various directions around a cell
//      2
//
//      +--+--+    +--+--+
//      |     |    |11 12|    11  12   a maze and its representation
//      +--+  +    +--+  +
//      |     |    |11 06|    11  06
//      +--+--+    +--+--+
//
//     16 16 16 16   initial maze contents returned by constructor
//     16 15 15 16
//     16 15 15 16
//     16 16 16 16
//
/*============================================================*/
import java.util.Random;

public class MyMaze {

   private int[][] m;   // maze representation
   private int rows;    // number of rows in the maze
   private int cols;    // number of columns in the maze
   private final static byte[] TWO = { 1, 2, 4, 8, 16};
   private final static byte[] DX  = { 0,+1, 0,-1};
   private final static byte[] DY  = {-1, 0,+1, 0};
   private boolean done;  // used in finding a single solution.
   private long   count;  // used in finding the number of solutions.
   private Random r;      // for generating random integers.
   
   boolean[][] visited;
   boolean[][] countsolution;

   public int getRows() { return( rows ); }
   public int getCols() { return( cols ); }

   public MyMaze ( int nr, int nc, int seed ) {
      r = new Random( seed );
      rows = nr;  cols = nc;
      m = new int[nr+2][nc+2];
      for (int r=1; r<=nr; ++r )
         for (int c=1; c<=nc; ++c )
            m[r][c] = 15;
      for (int r=0; r<nr+2; ++r )
            m[r][0] = m[r][nc+1] = 16;
      for (int c=0; c<nc+2; ++c )
         m[0][c] = m[nr+1][c] = 16;
      Create( nr/2+1, nc/2+1, 0 );
   }

   // Wall in direction p?
   public boolean ok ( int x, int y, int p ) {
      return( (m[x][y] & TWO[p]) == TWO[p] );
   }

   private boolean downWall( int x, int y, int p ) {
      if (ok(x,y,p) && m[x+DX[p]][y+DY[p]] != 16) {
         m[x][y] ^= TWO[p];  m[x+DX[p]][y+DY[p]] ^= TWO[p^2];
         return true;
      }
      return false;
   }

   private void knockDown( int count ) {
      // Caution: make sure there are at least count walls!
      for (int i=0; i<count; ++i) {
         int x = 1+r.nextInt(rows);
         int y = 1+r.nextInt(cols);
         if (!downWall( x, y, r.nextInt(4))) --i;
      }
   }

   private void Create ( int x, int y, int val ) {
      int[] perm = randPerm( 4 );
      m[x][y] ^= val;
      for (int i=0; i<4; ++i) {
         int p = perm[i];
         if (m[x+DX[p]][y+DY[p]] == 15) {
            m[x][y] ^= TWO[p];
            Create( x+DX[p], y+DY[p], TWO[p^2] );
         }
      }
   }

   private int[] randPerm( int n ) {
      // This algorithm should look familiar!
      int[] perm = new int[n];
      for (int k=0; k<n; ++k) perm[k] = k;
      for (int k=n; k>0; --k) {
         int rand = r.nextInt(k);
         int t = perm[rand];  perm[rand] = perm[k-1];  perm[k-1] = t;
      }
      return( perm );
   }

   
   
   
   
   public String toString() {
      // FOR YOU TO FILL IN.  MUST FOLLOW CORRECT FORMAT.
	  String mazee="";
	  for(int j=1; j<=this.rows;j++){
		 for(int i=1; i<=this.cols; i++){
			 String addmaze=String.format("%2d",this.m[j][i]);
		     mazee=mazee+addmaze+" ";
	     } 
		 mazee=mazee+"\n";
	  }  
	  return mazee;
   }

   public void solveMaze( int x0, int y0, int x1, int y1 ) {
      // FOR YOU TO CODE.
	  //(x0,y0)is starting point. (x1, y1)is ending point
	  visited=new boolean[x1+1][y1+1];
	  findPath(x0,y0);
   }
   
   private boolean findPath(int x, int y){
	   if( x==rows && y==cols ){
		   m[x][y]+=16;
		   count++;
		   return true;	   
	   }else if(x > 0 && x <= rows && y > 0 && y <= cols){
		   
		   if(!ok(x,y,3)&&!visited[x-1][y]){//move up
			   visited[x][y]=true;
			   done=findPath(x-1,y);
			   if(done==true){
				   m[x][y]+=16;
				   return true;
			   }
			   visited[x][y]=false;
		   }
		   
		   if(!ok(x,y,2)&&!visited[x][y+1]){//move right
			   visited[x][y]=true;
			   done=findPath(x,y+1);
			   if(done==true){
				   m[x][y]+=16;
				   return true;
			   }
			   visited[x][y]=false;
		   }
		   
		   if(!ok(x,y,1)&&!visited[x+1][y]){//move down
			   visited[x][y]=true;
			   done=findPath(x+1,y);
			   if(done==true){
				   m[x][y]+=16;
				   return true;
			   }
			   visited[x][y]=false;
		   }
		   
		   if(!ok(x,y,0)&&!visited[x][y-1]){//move left
			   visited[x][y]=true;
			   done=findPath(x,y-1);
			   if(done==true){
				   m[x][y]+=16;
				   return true;
			   }
			   visited[x][y]=false;
		   }			   
	   }	   
	   return false;	   
   }
   
   
   public long numSolutions( int x0, int y0, int x1, int y1 ) {
      // FOR YOU TO CODE.
	  //int[][] n=new int[m.length][m.length];
	  count=0;
	  countsolution=new boolean[x1+1][y1+1];
	  solutions(x0,y0);
	  return count;
	  
   } 
   
   private void solutions(int x, int y){
	   if( x==rows && y==cols ){
		   count++;
	   }else if(x > 0 && x <= rows && y > 0 && y <= cols){
		   
		   if(!ok(x,y,3)&&!countsolution[x-1][y]){//move up
			   countsolution[x][y] = true;
               solutions(x-1, y);
               countsolution[x][y] = false;
		   }
		   
		   if(!ok(x,y,2)&&!countsolution[x][y+1]){//move right
			   countsolution[x][y] = true;
               solutions(x, y+1);
               countsolution[x][y] = false;
		   }
		   
		   if(!ok(x,y,1)&&!countsolution[x+1][y]){//move down
			   countsolution[x][y] = true;
               solutions(x+1, y);
               countsolution[x][y] = false;
		   }
		   
		   if(!ok(x,y,0)&&!countsolution[x][y-1]){//move left
			   countsolution[x][y] = true;
               solutions(x, y-1);
               countsolution[x][y] = false;
		   }
			   
			   
	   }
	   
   }

   public static void main ( String[] args ) {
      int row = Integer.parseInt( args[0] );
      int col = Integer.parseInt( args[1] );
      int sx = Integer.parseInt( args[2] );
      int sy = Integer.parseInt( args[3] );
      int fx = Integer.parseInt( args[4] );
      int fy = Integer.parseInt( args[5] );
      MyMaze maz = new MyMaze( row, col, 9999 );
      System.out.print( maz );
      System.out.println( "Solutions = "+maz.numSolutions( sx, sy, fx, fy ) );
      maz.knockDown( (row+col)/4 );
      System.out.print( maz );
      System.out.println( "Solutions = "+maz.numSolutions( sx, sy, fx, fy ) );
      maz = new MyMaze( row, col, 9999 );  // creates the same maze anew.
      maz.solveMaze( sx, sy, fx, fy );
      System.out.print( maz );
   }
}