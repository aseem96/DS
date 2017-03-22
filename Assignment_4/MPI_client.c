#include "mpi.h" 
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAX 1000
 
int main( int argc, char **argv ) 
{ 
     MPI_Comm server; 
     MPI_Status status; 
     char port_name[MPI_MAX_PORT_NAME], str[MAX], ch; 
    
     int i, flag, tag;
 
	 MPI_Init( &argc, &argv ); 
     strcpy(port_name, argv[1] );
 
  	 MPI_Comm_connect( port_name, MPI_INFO_NULL, 0, MPI_COMM_WORLD, &server ); 
 
 	  // accept input string
	  printf("\nEnter the string :\n");
	  scanf("%s",str); 	
 
	 // Send string to server
 	 for (i = 0; i < strlen(str); i++) 
 	 {
		if(str[i] != '\0')
			ch=str[i];

	 	tag=2;
		MPI_Send(&ch, 1, MPI_CHAR, 0, tag, server);
	 }
    
	 // Sending done
	 MPI_Send(&i, 0, MPI_INT, 0, 1, server);
	
	 // Display reversed string
	 i=0;
	 flag=1;
	 while (flag) 
	 {
		 MPI_Recv(&ch, 1, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, server, &status);
		 switch (status.MPI_TAG) 
		 {
		 	 case 1: flag = 0;
			 		 break;
			 
			 case 2: str[i] = ch;
					 i++;
					 break;
			 
		}
	 }

 	printf("\nReversed string is : %s\n", str); 

    MPI_Comm_disconnect(&server); 
    MPI_Finalize(); 
    
  return 0; 
} 
