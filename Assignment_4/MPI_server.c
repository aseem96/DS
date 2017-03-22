#include "mpi.h" 
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#define MAX 1000

int main( int argc, char **argv ) 
{ 
    MPI_Comm client; 
    MPI_Status status; 
    char port_name[MPI_MAX_PORT_NAME]; 
    char str[MAX], ch, temp; 
    int size, flag, i=0, j=0; 
 
	MPI_Init( &argc, &argv ); 
    MPI_Comm_size(MPI_COMM_WORLD, &size); 
    
 	if (size != 1)
 	{
	 	fprintf(stderr, "Server too big");
	 	exit(EXIT_FAILURE);
 	} 
    
    MPI_Open_port(MPI_INFO_NULL, port_name); 
   	printf("Server running at %s\n", port_name); 
    
    while (1)
    { 
        MPI_Comm_accept( port_name, MPI_INFO_NULL, 0, MPI_COMM_WORLD, &client ); 
        flag = 1; 
       
        while (flag) 
        { 
            MPI_Recv( &ch, 1, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, client, &status ); 
            
            switch (status.MPI_TAG) 
            { 
                case 0: /* Finish */
                		MPI_Comm_free( &client ); 
                        MPI_Close_port(port_name); 
                        MPI_Finalize(); 
                        return 0; 
                        
                case 1:  printf("\nReceived String : %s\n", str);
					     
				// reverse the string
				i = 0;
				j = strlen(str) - 1;
				while (i < j)
				{
				  temp = str[i];
				  str[i] = str[j];
				  str[j] = temp;
				  i++;
				  j--;
				}
				  
				printf("\nReversed string is : %s\n", str);
				// send the reversed string to client (character by character)
				for (i = 0; i < strlen(str); i++)
				{
				 ch=str[i];
				 MPI_Send(&ch, 1, MPI_CHAR, 0, 2, client);
				}
                                //send tag=1 to indicate end of string
				MPI_Send(&ch, 1, MPI_CHAR, 0, 1, client); 
						
				MPI_Comm_disconnect( &client ); 
				flag = 0; 
				break; 
                        
                case 2: /* Accept string */ 
                		str[i++] = ch;
                		str[i] = '\0';
                		break;
                

                default: /* Erroneous message type */ 
                         MPI_Abort( MPI_COMM_WORLD, 1 ); 
            } 
        } 
    } 
}


