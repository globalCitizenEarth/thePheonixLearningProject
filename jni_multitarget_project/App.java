public class App {
    static {
        String arch = System.getProperty("os.arch");
        String dataModel = System.getProperty("sun.arch.data.model");
        String osName = System.getProperty("os.name").toLowerCase();

        String libToLoad = "native_x86_64"; // Default fallback

        if (dataModel.equals("32")) {
            libToLoad = "native_x86_32";
        } else {
            // In a real production app, you might use a tool like 'cpuid' 
            // or check /proc/cpuinfo via Java to distinguish Piledriver vs Bulldozer.
            // For this setup, we default to Piledriver as the primary 64-bit target.
            libToLoad = "native_piledriver";
        }

        try {
            System.out.println("[Java] OS: " + osName + " | Arch: " + arch + " (" + dataModel + "-bit)");
            System.out.println("[Java] Attempting to load: " + libToLoad);
            System.loadLibrary(libToLoad);
            System.out.println("[Java] Native library loaded successfully.");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("[Error] Could not load library: " + libToLoad);
            System.err.println("Ensure LD_LIBRARY_PATH includes the 'dist' folders.");
            System.exit(1);
        }
    }

    // Native method declarations (C and C++ flavors)
    public native void callCFunction();
    public native void callCppFunction();

    public static void main(String[] args) {
        App app = new App();
        app.callCFunction();
        app.callCppFunction();
    }
}