#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
void sigint_handler(int sent_signal)
{
  if (sent_signal == SIGINT)
  {
    printf("hello SIGINT\n");
    exit(0);
  }
}

void sigterm_handler(int sent_signal)
{
  if (sent_signal == SIGTERM)
  {
    printf("hello SIGTERM\n");
    exit(0);
  }
}




int main()
{
   printf("Starting process %d\n",getpid());
   signal(SIGINT, sigint_handler);
   signal(SIGTERM, sigterm_handler);
   int i;
   for (i = 0; i < 500; i++)
   {
      printf("%d\n",i);
      sleep(1);
   }
    return 0;
}
