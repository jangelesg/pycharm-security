package security

object Checks {
    val PyyamlUnsafeLoadCheck = CheckType("YML100", "Use of unsafe yaml load. Allows instantiation of arbitrary objects. Consider yaml.safe_load().")
    val FlaskDebugModeCheck = CheckType("FLK100", "Flask app appears to be run with debug=True, which exposes the Werkzeug debugger and allows the execution of arbitrary code.")
    val RequestsNoVerifyCheck = CheckType("RQ100", "Setting verify=False when using requests bypasses SSL verification and leaves requests susceptible to MITM attacks.")
    val HttpxNoVerifyCheck = CheckType("RQ101", "Setting verify=False when using httpx bypasses SSL verification and leaves requests susceptible to MITM attacks.")
    val SubprocessShellCheck = CheckType("PR100", "Calling subprocess commands with shell=True can leave the host shell open to local code execution or remote code execution attacks.")
    val TempfileMktempCheck = CheckType("TMP100", "The way that tempfile.mktemp creates temporary files is insecure and leaves it open to attackers replacing the file contents. Use tempfile.mkstemp instead.")
    val DjangoDebugModeCheck = CheckType("DJG100", "Running Django in Debug mode is highly insecure and should only be used for local development purposes.")
    val DjangoRawSqlCheck = CheckType("DJG101", "Using quoted, parametrized literal will bypass Django SQL Injection protection.")
    val DjangoSafeStringCheck = CheckType("DJG102", "Using safe strings bypasses the Django XSS protection.")
    val DjangoCsrfMiddlewareCheck = CheckType("DJG200", "Django middleware is missing CsrfViewMiddleware, which blocks cross-site request forgery.")
    val DjangoClickjackMiddlewareCheck = CheckType("DJG201", "Django middleware is missing XFrameOptionsMiddleware, which blocks clickjacking.")
    val InsecureHashAlgorithms = CheckType("HL100", "MD4, MD5, SHA, and SHA1 hashing algorithms have cryptographically weak algorithms and should not be used for obfuscating or protecting data.")
    val LengthAttackHashAlgorithms = CheckType("HL101", "MD5, SHA-1, RIPEMD-160, Whirlpool, and the SHA-256 / SHA-512 hash algorithms are all vulnerable to length-extension attacks and should not be used for obfuscating or protecting data. Use within a HMAC is not vulnerable.")
    val TimingAttackCheck = CheckType("PW100", "Matching inputs, secrets or tokens using the == operator is vulnerable to timing attacks. Use compare_digest() instead.")
    val HardcodedPasswordCheck = CheckType("PW101", "Passwords, secrets or keys should not be hardcoded into Python code.")
    val JinjaAutoinspectCheck = CheckType("JJ100", "Jinja does not inspect or sanitize input by default, leaving rendered templates open to XSS. Use autoinspect=True.")
    val BuiltinExecCheck = CheckType("EX100", "Use of builtin exec function for dynamic input is insecure and can leave your application open to arbitrary code execution.")
    val MakoTemplateFilterCheck = CheckType("MK100", "Mako does not inspect or sanitize input by default, leaving rendered templates open to XSS. Use default_filters=['h'].")
    val SqlInjectionCheck = CheckType("SQL100", "Possible SQL injection within String format.")
    val AssertCheck = CheckType("AST100", "Asserts should only be used in tests. Asserts are typically bypassed in a production environment.")
    val TryExceptPassCheck = CheckType("TRY100", "Ignoring exceptions without either logging or handling is not considered good security practice.")
    val TryExceptContinueCheck = CheckType("TRY101", "Ignoring exceptions without either logging or handling is not considered good security practice.")
    val ParamikoHostkeyBypassCheck = CheckType("PAR100", "Paramiko set to automatically trust the host key.")
    val BindAllInterfacesCheck = CheckType("NET100", "Possible hardcoded binding to all network interfaces.")
    val ChmodInsecurePermissionsCheck = CheckType("OS100", "Modification of system files to allow execution.")
    val PickleLoadCheck = CheckType("PIC100", "Loading serialized data with the pickle module can expose arbitrary code execution using the __reduce__ method.")

    class CheckType(var Code: String, private var Message: String) {
        override fun toString(): String {
            return "$Code: $Message"
        }

        private fun getLink(): String {
            return "<a href='https://pycharm-security.readthedocs.io/en/latest/checks/$Code.html'>See $Code documentation</a>"
        }

        fun getDescription(): String {
            return "$this Found in '#ref'."
        }

        fun getStaticDescription(): String {
            return this.toString() + " " + this.getLink()
        }
    }
}