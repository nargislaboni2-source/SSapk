import contextlib
import io
import traceback


def run_code(source):
    """Run a short user-provided Python script and return printed output."""
    output = io.StringIO()
    scope = {"__name__": "__main__"}
    try:
        with contextlib.redirect_stdout(output), contextlib.redirect_stderr(output):
            exec(compile(source, "<SS-Python-Project>", "exec"), scope, scope)
    except Exception:
        traceback.print_exc(file=output)
    result = output.getvalue()
    return result if result else "Python finished successfully (no output)."
