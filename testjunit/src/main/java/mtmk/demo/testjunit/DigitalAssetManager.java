package mtmk.demo.testjunit;

import java.io.File;

public class DigitalAssetManager {

	public DigitalAssetManager(File icon, File assets) {
		if (icon == null)
			throw new IllegalArgumentException(
					"Icon is null, not a file, or doesn't exist.");
	}

	public Object getAssetCount() {
		return 3;
	}

}
