import com.example.random.service.RandomNumberService;

module com.example.lottery {
	requires java.base;
	requires java.logging;
	requires com.example.random;
	uses RandomNumberService;
}